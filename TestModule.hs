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

cityError0 = newC "" p0
cityExceptions = [
    testF cityError0,
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

qualityError0 = newQ "" 4 2.1
qualityError1 = newQ "" (-1) 1.0
qualityError2 = newQ "a" 1 (-10.9)

qualityExceptions = [
    testF qualityError0, --No se especifica el material de la calidad
    testF qualityError1, --Se introduce una capacidad negativa
    testF qualityError2, --Se introduce una demora negative
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

linkError0 = newL c0 c0 q0
linkError1 = linksL c0 c0 l0
linkExceptions = [
    testF linkError0, --arroja excepción por intentar crear un link entre una sola ciudad.
    testF linkError1, --arroja excepción porque un link no puede conectar una ciudad.
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

tunelError0 = newT []
tunelExceptions = [
    testF tunelError0, --No es posible crear un tunel vacío
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

regionError1 = foundR r2 c1 
regionError2 = foundR r2 c4
regionError3 = foundR r2 c5
regionError4 = linkR r5 c5 c0 q0
regionError5 = linkR r5 c0 c1 q0
regionError6 = delayR r6 c5 c4 
regionError7 = delayR r6 c0 c1
regionError8 = availableCapacityForR r6 c5 c4
regionError9 = availableCapacityForR r6 c0 c2
regionExceptions = [
    testF regionError1, -- la ciudad ya existe
    testF regionError2, -- ya hay una ciudad con esa ubicación en la región
    testF regionError3, -- ya hay una ciudad con ese nombre en la región
    testF regionError4, -- las ciudades no se encuentran en la región
    testF regionError5, -- el link ya existe en la región
    testF regionError6, -- las ciudades no están dentro de la región
    testF regionError7, -- no existe un tunel que conecte las dos ciudades
    testF regionError8, -- las ciudades no estan en la región
    testF regionError9, -- el enlace entre las ciudades no existe
    True]
