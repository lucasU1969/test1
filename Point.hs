module Point ( Point, newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP x y = Poi x y

difP :: Point -> Point -> Float  -- distancia absoluta
difP po1 
