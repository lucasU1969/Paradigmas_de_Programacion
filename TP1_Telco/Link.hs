module Link ( Link, newL, linksL, connectsL, capacityL, delayL )
   where

import Quality
import City
import Point

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality | city1 == city2 = error "No es posible crear un link entre una ciudad." 
                         | otherwise = Lin city1 city2 quality

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city (Lin city1 city2 _) = (city == city1) || (city == city2)

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 link | city1 == city2 = error "Las ciudades ingresadas son iguales."
                        | otherwise = (connectsL city1 link) && (connectsL city2 link)

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin c1 c2 quality) = (delayQ quality) * (distanceC c1 c2)
