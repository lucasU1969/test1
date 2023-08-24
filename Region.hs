module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR )
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
foundR (Reg cities links tunnels) city | elem city cities = error "la ciudad ya existe"
                                       | otherwise = Reg (city:cities) links tunnels

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunnels) c0 c1 qua | not ((elem c0 cities) && (elem c1 cities)) = error "las ciudades no se encuentran en la región."
                                           | linkedR (Reg cities links tunnels) c0 c1 = error " el enlace ya existe."
                                           | otherwise = Reg cities ((newL c0 c1 qua):links) tunnels

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR region cities = addtuneltoR region (constructtunelR region cities)

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunels) c0 c1 = foldr ((||).(connectsT c0 c1)) False tunels 

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _ ) c0 c1 = foldr ((||).(linksL c0 c1)) False links

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR (Reg cities links tunnels) c0 c1 | not ((elem c0 cities) && (elem c1 cities)) = error "las ciudades no se encuentran en la región."
                                    | not (connectedR (Reg cities links tunnels) c0 c1) = error "no existe un tunel que conecte las ciudades."
                                    | otherwise =  head [delayT tunel| tunel<-tunnels, connectsT c0 c1 tunel]

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
availableCapacityForR (Reg cities links tunels) c0 c1 | not (elem c0 cities && elem c1 cities) = error "las cidades no estan en la region."
                                                      | not (linkedR (Reg cities links tunels) c0 c1) = error "el enlace no existe. "
                                                      | otherwise = getcapacitybtwnR c0 c1 links - countTunnelsUsingR c0 c1 tunels

countTunnelsUsingR :: City -> City -> [Tunel] -> Int
countTunnelsUsingR c1 c2 [] = 0
countTunnelsUsingR city1 city2  (x:xs) | usesT (newL city1 city2 (newQ "" 1 1)) x = 1 + countTunnelsUsingR city1 city2 xs
                                       | otherwise = countTunnelsUsingR city1 city2 xs

getcapacitybtwnR :: City -> City -> [Link] -> Int
getcapacitybtwnR city1 city2 links = head [capacityL link | link<-links, linksL city1 city2 link]

getlinkconnectingR :: City -> City -> [Link] -> Link
getlinkconnectingR c0 c1 links = head [link | link<-links , linksL c0 c1 link]

constructtunelR :: Region -> [City] -> [Link]
constructtunelR region [] = []
constructtunelR region [c0] = error "no hay tunel entre una sola ciudad"
constructtunelR (Reg cities links tunels) [c0, c1] = [getlinkconnectingR c0 c1 links]
constructtunelR (Reg cities links tunels) (c0:(c1:cs)) | not (elem c0 cities && elem c1 cities) = error "no todas las ciudades se encuentran en la region"
                                                       | not (linkedR (Reg cities links tunels) c0 c1) = error "los enlaces no existen."
                                                       | availableCapacityForR (Reg cities links tunels) c0 c1 == 0 = error "los enlaces no soportan el tunel."
                                                       | otherwise = (getlinkconnectingR c0 c1 links: constructtunelR (Reg cities links tunels) (c1:cs))

addtuneltoR :: Region -> [Link] -> Region 
addtuneltoR region [] = region
addtuneltoR (Reg cities ls tunels) links = (Reg cities ls ((newT links):tunels))
