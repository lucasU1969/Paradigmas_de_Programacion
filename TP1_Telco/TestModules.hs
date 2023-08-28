import Point
import City
import Link
import Quality
import Tunel
import Region
import TestF

---------------------
--TEST POINT MODULE--
---------------------
p0 :: Point
p0 = newP 1 1
p1 :: Point
p1 = newP 4 5
p2 :: Point
p2 = newP 0 1
p3 :: Point
p3 = newP 1 6
p4 :: Point
p4 = newP 5 9

testPoint = [
   newP 1 1 == p0,
   difP p0 p1 == 5.0,
   True]

--------------------
--TEST CITY MODULE--
--------------------
c0 :: City
c0 = newC "A" p0
c1 :: City
c1 = newC "B" p1
c2 :: City 
c2 = newC "C" p2
c3 :: City
c3 = newC "D" p3
c4 :: City
c4 = newC "E" p0
c5 :: City
c5 = newC "A" p4

testCity = [
    nameC c0 == "A",
    distanceC c0 c1 == 5.0,
    True]

cError0_NoName = newC "" p0
cityExceptions = [
    testF cError0_NoName, --Exception: El nombre de la ciudad no puede ser vacío.
    True]

-----------------------
--TEST QUALITY MODULE--
-----------------------
q0 :: Quality
q0 = newQ "cobre" 3 2.3
q1 :: Quality 
q1 = newQ "estaño" 3 2.2
q2 :: Quality 
q2 = newQ "f" 9 1.2

testQuality = [
    capacityQ q0 == 3,
    delayQ q0 == 2.3,
    True]

qError0_NoMaterial = newQ "" 4 2.1
qError1_NegativeCapacity = newQ "fibra óptica" (-1) 1.0
qError2_NegativeDelay = newQ "cobre" 1 (-1.9)
qualityExceptions = [
    testF qError0_NoMaterial, --Exception: Se debe especificar un material. 
    testF qError1_NegativeCapacity, --Exception: La capacidad de una calidad no puede ser menor a 0.
    testF qError2_NegativeDelay, --Exception: La demora de una calidad no puede ser menor a 0.
    True]

--------------------
--TEST LINK MODULE--
--------------------
l0 :: Link
l0 = newL c0 c1 q0
l1 :: Link
l1 = newL c1 c2 q1
l2 :: Link
l2 = newL c2 c3 q2

testLink = [ 
    connectsL c0 l0,
    not(connectsL c3 l0),
    linksL c0 c1 l0,
    linksL c1 c0 l0,
    not(linksL c3 c1 l0),
    capacityL l0 == 3,
    delayL l0 == 11.5,
    True]

lError0_SameCity = newL c0 c0 q0
lError1_SameCity = linksL c0 c0 l0
linkExceptions = [
    testF lError0_SameCity, --Exception: No es posible crear un link entre una ciudad.
    testF lError1_SameCity, --Exception: Las ciudades ingresadas son iguales.
    True]

---------------------
--TEST TUNEL MODULE--
---------------------
t0 :: Tunel
t0 = newT [l0, l1, l2]

test_tunel = [
    newT [l0, l1, l2] == t0,
    not(connectsT c0 c1 t0),
    connectsT c0 c3 t0,
    usesT l2 t0,
    delayT t0 == delayL l0 + delayL l1 + delayL l2,
    True]

tError0_NoLinks = newT []
tunelExceptions = [
    testF tError0_NoLinks, --Exception: No es posible crear un tunel sin enlaces.
    True]

----------------------
--TEST REGION MODULE--
----------------------
r0 :: Region
r0 = newR
r1 :: Region
r1 = foundR r0 c0
r2 :: Region
r2 = foundR r1 c1
r3 :: Region
r3 = foundR r2 c2

r4 :: Region
r4 = linkR r3 c0 c1 q0
r5 :: Region 
r5 = linkR r4 c1 c2 q1

r6 :: Region
r6 = tunelR r5 [c0, c1, c2]

testRegion = [
    connectedR r6 c0 c2,
    connectedR r6 c2 c0,
    not(connectedR r6 c1 c2),
    linkedR r4 c0 c1,
    linkedR r4 c1 c0,
    not(linkedR r5 c0 c2),
    delayR r6 c0 c2 == 23.94508,
    availableCapacityForR r5 c0 c1 == 3,
    availableCapacityForR r6 c0 c1 == 2,
    True]

rError0_CityAlreadyIn = foundR r2 c1 
rError1_UsedLocation = foundR r2 c4
rError2_RepeatedName = foundR r2 c5
rError3_CityNotinRegion = linkR r5 c5 c0 q0
rError4_ExistingLink = linkR r5 c0 c1 q0
rError5_CityNotinRegion = delayR r6 c5 c4 
rError6_NoTunelBetween = delayR r6 c0 c1
rError7_CityNotInRegion = availableCapacityForR r6 c5 c4
rError8_NoLinkBetween = availableCapacityForR r6 c0 c2
regionExceptions = [
    testF rError0_CityAlreadyIn, --Exception: La ciudad ingresada ya se encuentra en la región.
    testF rError1_UsedLocation, --Exception: Ya hay una ciudad en esa ubicacion dentro de la región.
    testF rError2_RepeatedName, --Exception: Ya hay una ciudad en la región con el nombre.
    testF rError3_CityNotinRegion, --Exception: Alguna de las ciudades no se encuentra en la región.
    testF rError4_ExistingLink, --Exception: El enlace ya existe.
    testF rError5_CityNotinRegion, --Exception: Alguna de las ciudades no se encuentra en la región.
    testF rError6_NoTunelBetween, --Exception: No existe un tunel dentro de la región que conecte las ciudades.
    testF rError7_CityNotInRegion, --Exception: Alguna de las ciudades no se encuentran en la región.
    testF rError8_NoLinkBetween, --Exception: El enlace entre las ciudades no existe.
    True]
