module Quality ( Quality, newQ, capacityQ, delayQ )
   where

data Quality = Qua String Int Float deriving (Eq, Show)

newQ :: String -> Int -> Float -> Quality
newQ material capacity delay | material == "" = error "Se debe especificar un material."
                             | capacity < 0 = error "La capacidad de una calidad no puede ser menor a 0."
                             | delay < 0 = error "La demora de una calidad no puede ser menor a 0."
                             | otherwise = Qua material capacity delay

capacityQ :: Quality -> Int -- cuantos túneles puede tolerar esta conexión
capacityQ (Qua _ capacity _) = capacity

delayQ :: Quality -> Float  -- la demora por unidad de distancia que sucede en las conexiones de este canal
delayQ (Qua _ _ delay) = delay

