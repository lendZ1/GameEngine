# Engine
A simple 2D game engine

Main:
Creates a Game and a GameLoop object.

Game:
Creates instances of Window and GamePanel.
Player must be added before other game objects.

GameLoop:
Updates the Game at a tick rate. Calls GamePanel.update() every tick.

GameObject:
Threads that represent figures in the game. Has position and size to calculate collisions.
All objects are currently rectangles with height, width, and color.

GOBuilder:
Builder class for GameObject

Player:
Subclass of GameObject to handle user input. 

GamePanel:
The game should have one panel in the window that draws GameObjects.
update() calls OppdaterPosisjon for all GameObjects.

Window:
Initializes and manages the window itself.

Map:
Controls the area the objects are on, including areas outside the window.

Also holds a TreeMap<Integer, ArrayList<GameObject>> that contains all GameObjects.
The integer is the layer, where 1 is the bottom layer, and the value is an ArrayList of all GameObjects on that layer.