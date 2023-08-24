module Tunel ( Tunel, newT, connectsT, usesT, delayT, )
   where

import Link
import City
import Data.List

data Tunel = Tun [Link] deriving (Eq, Show)

newT :: [Link] -> Tunel
newT links = Tun links

connectsT :: City -> City -> Tunel -> Bool -- inidca si este tunel conceta estas dos ciudades distintas
connectsT c0 c1 (Tun []) = False
connectsT c0 c1 (Tun ls) = ((isbeginningofT c0 ls) && (isEndingofT c1 ls)) || ((isbeginningofT c1 ls) && (isEndingofT c0 ls))

usesT :: Link -> Tunel -> Bool  -- indica si este tunel atraviesa ese link
usesT link (Tun links) = foldr ((||).(eqL link)) False links

delayT :: Tunel -> Float -- la demora que sufre una conexion en este tunel
delayT (Tun links) = foldr ((+).delayL) 0 links

isbeginningofT :: City -> [Link] -> Bool
isbeginningofT city [] = False
isbeginningofT city [l0] = connectsL city l0
isbeginningofT city (l0:(l1:ls)) = (connectsL city l0) && (not (connectsL city l1))

isEndingofT :: City -> [Link] -> Bool
isEndingofT city links = isbeginningofT city (reverse links)
-- dar vuelta la lista de links
