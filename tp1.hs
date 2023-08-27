import City
import Point
import Link
import Quality
import Tunel
import Region
import TestF
punto0 :: Point
punto0 = newP 1 2

punto1 :: Point
punto1 = newP 6 3

punto2 :: Point
punto2 = newP 0 1

punto3 :: Point
punto3 = newP 1 6

punto4 :: Point
punto4 = newP 5 9

punto5 :: Point
punto5 = newP 2 8

punto6 :: Point
punto6 = newP 3 4

punto7 :: Point
punto7 = newP 3 9

c0 :: City
c0 = newC "A" punto0
c1 :: City
c1 = newC "B" punto1
c2 :: City 
c2 = newC "C" punto2
c3 :: City
c3 = newC "D" punto3 
c4 :: City
c4 = newC "E" punto4
c5 :: City
c5 = newC "F" punto5
c6 :: City
c6 = newC "" punto6
c7 :: City
c7 = newC "G" punto0
c8 :: City
c8 = newC "B" punto7


q0 :: Quality
q0 = newQ "cobre" 3 2.3

q1 :: Quality 
q1 = newQ "estaño" 3 2.2

q2 :: Quality 
q2 = newQ "f" 9 1.2

q3 :: Quality
q3 = newQ "" 4 2.1

q1234 = newQ "" (-1) 1.0
q23 = newQ "a" 1 (-10.9)


l0 :: Link
l0 = newL c0 c1 q0
l1 :: Link
l1 = newL c2 c3 q1
l2 :: Link
l2 = newL c4 c5 q2
t0 :: Tunel
t0 = newT [l0, l1, l2]


z = newR 
z1 = foundR z c0 
z2 = foundR z1 c1 
z3 = foundR z2 c2

regionerror1 = foundR z2 c1 
regionerror2 = foundR z2 c7
regionerror3 = foundR z2 c8
z4 = linkR z3 c0 c1 q0
z5 = linkR z4 c1 c2 q1
regionerror4 = linkR z5 c3 c4 q2
regionerror5 = linkR z5 c0 c1 q0
z6 = tunelR z5 [c0, c1, c2]

regionerror6 = delayR z6 c5 c6
regionerror7 = delayR z6 c0 c1

regionerror8 = availableCapacityForR z6 c5 c6
regionerror9 = availableCapacityForR z6 c0 c2

l3 :: Link
l3 = newL c0 c1 q0
l4 :: Link
l4 = newL c1 c2 q1
l5 :: Link
l5 = newL c2 c3 q2
l6 :: Link
l6 = newL c3 c0 q1
l7 :: Link 
l7 = newL c0 c1 q1

t1 :: Tunel
t1 = newT [l3, l4, l5, l6, l7]
t2 :: Tunel
t2 = newT [l3, l4]
t3 :: Tunel
t3 = newT [l3, l4]
t4 :: Tunel
t4 = newT [l2, l5, l3]
t5 :: Tunel
t5 = newT [l1, l2, l6]
t6 :: Tunel
t6 = newT []

test_point = [
   newP 0 1 == punto2,
   difP punto0 punto1 == sqrt 26,
   True]

test_tunel = [
    newT [l3, l4] == t2,
    not(connectsT c0 c1 t6),
    connectsT c0 c2 t3,
    usesT l2 t4,
    delayT t5 == delayL l1 + delayL l2 + delayL l6,
    True]



test_f = [
    testF c6,
    testF q3,
    testF q1234,
    testF q23,
    testF t6,
    testF regionerror1, -- la ciudad ya existe
    testF regionerror2, -- ya hay una ciudad con esa ubicación en la región
    testF regionerror3, -- ya hay una ciudad con ese nombre en la región
    testF regionerror4, -- las ciudades no se encuentran en la región
    testF regionerror5, -- el link ya existe en la región
    testF regionerror6, -- las ciudades no están dentro de la región
    testF regionerror7, -- no existe un tunel que conecte las dos ciudades
    testF regionerror8, -- las ciudades no estan en la región
    testF regionerror9 -- el enlace entre las ciudades no existe
 ]


