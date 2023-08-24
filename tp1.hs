import City
import Point
import Link
import Quality
import Tunel
import Region

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

a :: City
a = newC "A" punto0

b :: City
b = newC "B" punto1

c :: City 
c = newC "C" punto2

d :: City
d = newC "D" punto3 

e :: City
e = newC "E" punto4

f :: City
f = newC "F" punto5


q0 :: Quality
q0 = newQ "cobre" 3 2.3

q1 :: Quality 
q1 = newQ "estaño" 3 2.2

q2 :: Quality 
q2 = newQ "f" 9 1.2


l0 :: Link
l0 = newL a b q0

l1 :: Link
l1 = newL c d q1

l2 :: Link
l2 = newL e f q2

t0 :: Tunel
t0 = newT [l0, l1, l2]

z = newR 

z1 = foundR z a 
z2 = foundR z1 b 
z3 = foundR z2 c 

z4 = linkR z3 a b q0

z5 = linkR z4 b c q1

z6 = tunelR z5 [a, b, c]

l3 :: Link
l3 = newL a b q0
l4 :: Link
l4 = newL b c q1
l5 :: Link
l5 = newL c d q2
l6 :: Link
l6 = newL d a q1
l7 :: Link 
l7 = newL a b q1

t1 :: Tunel
t1 = newT [l3, l4, l5, l6, l7]

t2 :: Tunel
t2 = newT [l3, l4]
