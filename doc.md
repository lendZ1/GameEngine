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
Skal styre området objektene er på inkludert områdene som er utenfor vinduet. 

Holder også et TreeMap<Integer, ArrayList<GameObject>> som holder alle GameObjects.
integer er hvilket nivå de er på der 1 er nederst. og verdien er en Arraylist med alle GameObjects på det nivået.
