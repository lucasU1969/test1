module Tunel ( Tunel, newT, connectsT, usesT, delayT )
   where

import Link
import City

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
{-connectsT city1 city2 tunel = if length([link | link <-tunel, linksL city1 city2 link]) >= 1 then True else False
-}
connectsT city1 city2 (Tun []) = False
connectsT city1 city2 (Tun (x:xs)) =  (linksL city1 city2 x) || (connectsT city1 city2 (Tun xs))

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = elem link links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT t = 1.2{-esto lo hice asi nomas para que no tire error 
-}
{-vamos a hacer que devuelelva la demora de algun link aleatorio del tunel-}
