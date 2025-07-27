Main:
oppretter et Game og et GameLoop objekt.

Game:
oppretter en instanser av vindu og GamePanel

GameLoop
oppdater Game med tickrate. kaller GamePanel.update() for hver tick.

GameObject:
tråder som skal representere figurer i spillet. Har posisjon og størrelse for å kunne beregne kollisjoner.
alle objekter er foreløpig firkanter med høyde, bredde og farge

GamePanel:
spillet skal ha ett panel i vinduet som skal tegne GameObject. 
update() kaller OppdaterPosisjon for alle GameObjects

Vindu:
initialiserer og styrer selve vinduet

Map:
Skal styre området objektene er på inkludert områdene som er utenfor vinduet