module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR )
   where

import City
import Link
import Tunel
import Quality
import Data.List

data Region = Reg [City] [Link] [Tunel] deriving (Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg cities links tunels) city | elem city cities = error "La ciudad ingresada ya se encuentra en la región."
                                      | foldr ((||).(== 0).(distanceC city)) False cities = error "Ya hay una ciudad en esa ubicación dentro de la región."
                                      | foldr ((||).(== nameC city).(nameC)) False cities = error "Ya hay una ciudad en la región con el nombre."
                                      | otherwise = Reg (city:cities) links tunels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) c0 c1 qua | not ((elem c0 cities) && (elem c1 cities)) = error "Alguna de las ciudades no se encuentra en la región."
                                          | linkedR (Reg cities links tunels) c0 c1 = error "El enlace ya existe."
                                          | otherwise = Reg cities ((newL c0 c1 qua):links) tunels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR region cities = addtuneltoR region (constructtunelR region cities)

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunels) c0 c1 = foldr ((||).(connectsT c0 c1)) False tunels 

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _ ) c0 c1 = foldr ((||).(linksL c0 c1)) False links

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora 
delayR region c0 c1 | not (verifycityinR region c0 && verifycityinR region c1) = error "Alguna de las ciudades no se encuentra en la región."
                    | not (connectedR region c0 c1) = error "No existe un tunel dentro de la región que conecte las ciudades."
                    | otherwise = head [delayT tunel| tunel<-(tunelsinR region), connectsT c0 c1 tunel]

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades 
availableCapacityForR region c0 c1 | not (verifycityinR region c0 && verifycityinR region c0) = error "Alguna de las ciudades no se encuentra en la región."
                                   | not (linkedR region c0 c1) = error "El enlace entre las ciudades no existe."
                                   | otherwise = totalCapacity region c0 c1 - usedCapacity region (getlinkconnectingR c0 c1 (linksinR region)) 

totalCapacity :: Region -> City -> City -> Int
totalCapacity (Reg _ links _) c0 c1 = head [capacityL link | link<-links, linksL c0 c1 link]

usedCapacity :: Region -> Link -> Int
usedCapacity (Reg _ _ []) link = 0
usedCapacity (Reg cs ls (t0:ts)) link | usesT link t0 = 1 + usedCapacity (Reg cs ls ts) link
                                      | otherwise = usedCapacity (Reg cs ls ts) link

getlinkconnectingR :: City -> City -> [Link] -> Link
getlinkconnectingR c0 c1 links = head [link | link<-links , linksL c0 c1 link]

addtuneltoR :: Region -> [Link] -> Region 
addtuneltoR region [] = region
addtuneltoR (Reg cities ls tunels) links = (Reg cities ls ((newT links):tunels))

citiesinR :: Region -> [City]
citiesinR (Reg cities _ _) = cities

linksinR :: Region -> [Link]
linksinR (Reg _ links _) =  links

tunelsinR :: Region -> [Tunel]
tunelsinR (Reg _ _ tunels) = tunels

constructtunelR :: Region -> [City] -> [Link]
constructtunelR region [] = []
constructtunelR region [c0] = error "No es posible crear un tunel con una única ciudad."
constructtunelR region [c0, c1] = [getlinkconnectingR c0 c1 (linksinR region)]
constructtunelR region (c0:(c1:cs)) | not (verifycityinR region c0 && verifycityinR region c1) = error "Alguna de las ciudades no se encuentra en la región."
                                    | not (linkedR region c0 c1) = error "Un enlace necesario para la construcción no se encuentra en la región."
                                    | availableCapacityForR region c0 c1 == 0 = error "los enlaces no soportan el tunel."
                                    | c0 == (last cs) = error "No se puede hacer un tunel que empiece y termine en la misma ciudad."
                                    | otherwise = (getlinkconnectingR c0 c1 (linksinR region): constructtunelR region (c1:cs))

verifycityinR :: Region -> City -> Bool
verifycityinR region city = elem city (citiesinR region) 
