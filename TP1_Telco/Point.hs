module Point ( Point, newP, difP)
   where

data Point = Poi Int Int deriving (Eq, Show)

newP :: Int -> Int -> Point
newP x y = Poi x y

coordX :: Point -> Int
coordX (Poi x _) = x 

coordY :: Point -> Int 
coordY (Poi _ y) = y

difP :: Point -> Point -> Float  -- distancia absoluta
difP p1 p2 = sqrt ((fromIntegral ((coordX p1) - (coordX p2))**2) +(fromIntegral ((coordY p1) - (coordY p2))**2))
--difP p1 p2 = sqrt ((x1 - x2) ** 2 + (y1 - y2) ** 2)
--   where x1 =  fromIntegral coordX p1

