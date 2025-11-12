A simplistic 2D game engine

# Main
Creates a Game and a GameLoop object.

# Game
Creates instances of the Window and GamePanel.  
Player must be added before other game objects.

# GameLoop
Updates the Game at a fixed tick rate. Calls GamePanel.update() on each tick.

# GameObject
Threads that represent entities in the game. Have position and size for collision calculations.  
All objects are currently rectangles with height, width and color.

# GamePanel
The game has a single panel in the window that draws GameObjects.  
update() calls updatePosition for all GameObjects.

# Window (Vindu)
Initializes and manages the window.

# GameMap
Manages the area where objects exist, including areas outside the window.  
Holds a TreeMap<Integer, ArrayList<GameObject>> containing all GameObjects — the integer is the layer (1 = bottom) and the value is the ArrayList of GameObjects on that layer.