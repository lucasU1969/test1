{-module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where-}

module Region (Region, newR, foundR, citiesinR, linksinR, tunelsinR, linkR)
   where

import City
import Link
import Tunel
import Quality
import Data.List

data Region = Reg [City] [Link] [Tunel] deriving (Show)


newR :: Region
newR = Reg [] [] []


foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR region city | elem city (citiesinR region) = error " la ciudad ya existe"
                   | otherwise = Reg (city:(citiesinR region)) (linksinR region) (tunelsinR region)


linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR reg c1 c2 qua | not ((iscityinR c1 reg) && (iscityinR c2 reg)) = error "las ciudades no se encuentran en la región."
                   | (islinkinR (newL c1 c2 qua) reg) = error " el enlace ya existe."
                   | otherwise = Reg (citiesinR reg) ((newL c1 c2 qua):linksinR reg) (tunelsinR reg)
{-
tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
>hay q ver que todos los links entre las ciudades existen y crear un tunel que las conecte
>chequear que la capacidad disponible de los links permita un tunel. 

-connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
-linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
> dos ciudades estan conectadas si un tunel empieza una y termina en la otra
delayR reg c1 c2 | not connectedR reg c1 c2 = error "no existe un tunel que conecte las ciudades."
                 | otherwise =  head [delayT tunel| tunel<-(tunelsinR reg), connectsT c1 c2 tunel]
availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
-}



citiesinR :: Region -> [City]
citiesinR (Reg cities _ _) = cities

linksinR :: Region -> [Link]
linksinR (Reg _ links _ ) = links

tunelsinR :: Region -> [Tunel]
tunelsinR (Reg _ _ tunels) = tunels

iscityinR :: City ->  Region ->  Bool
iscityinR city region = elem city (citiesinR region)

islinkinR :: Link -> Region -> Bool
--islinkinR link region = elem link (linksinR region)
islinkinR link region =  length (intersectBy (\x y -> eqL x y) [link] (linksinR region)) == 1

--istunelinR :: Tunel -> Region -> Bool
--istunelinR tunel region = length (intersectBy (\x y -> eqT x y) [tunel] (tunelsinR region)) == 1
