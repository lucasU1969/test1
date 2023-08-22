module Region ( Region, newR, foundR, linkR, tunelR, pathR, linksForR, connectedR, linkedR, delayR, availableCapacityForR, usedCapacityForR )
   where

import Link
import City 
import Tunel 

data Region = Reg [City] [Link] [Tunel]
newR :: Region
newR Cit (city) Lin (link) Tun (tunel) = Reg city link tunel
foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR (Reg  cities _ _) new_city | length([city| city <- cities, city == new_city])>= 1 = (Reg [cities] _ _)
                                  | otherwhise cities: new_city

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg _ _ tunnels) city1 city2 = foldr(\ tunel bool -> connectsT city1 city2 tunel && bool) True tunnels
                                              
linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
linkedR (Reg _ links _) city1 city2 = foldr(\ link bool -> linksL city1 city2 link && bool) True links

--linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
--tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan conectadas por un tunel
connectedR (Reg  _ _ tunnels) city1 city2 = foldr(tunel bool -> connectsT city1 city2 tunel && fold) True tunnels
--linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades estan enlazadas
--delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
--availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades