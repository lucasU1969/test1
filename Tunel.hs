module Tunel ( Tunel, newT, connectsT, usesT, delayT, citiesinT )
   where

import Link
import City
import Data.List

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT city1 city2 (Tun []) = False
connectsT city1 city2 tunel = ((connectsL city1 elem0)  && (connectsL city2 elem1)) || ((connectsL city2 elem0) && (connectsL city1 elem1))
   where 
      elem0 = (citiesinT tunel) !! 0
      elem1 = (citiesinT tunel) !! 1

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = elem link links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = foldr ((+).delayL) 0 links

citiesinT :: Tunel -> [City]
citiesinT (Tun links) = nub [(citiesinL link) | link<-links] {-esto tira error, lo estoy arreglando :>-}
