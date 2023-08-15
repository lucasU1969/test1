module City ( City, newC, nameC, distanceC )
   where

import Point 

data City = Cit String Point deriving (Eq, Show)

newC :: String -> Point -> City
newC = Cit


nameC :: City -> String
nameC (Cit name _) = name

pointC :: City -> Point
pointC (Cit _ point) = point


distanceC :: City -> City -> Float
distanceC city1 city2 = difP (pointC city1) (pointC city2)
