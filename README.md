# GameEngine


Main:
Creates a GWindow and a uiPanel

Game:
Creates instance of GameMap and GamePanel, and handles creation of all gameobjects. Player must be added before other game objects.
startGame() creates a new GameLoop and starts the game

GameLoop:
TICK_RATE is the amount of updates per second. Updates the game by calling GameMap.update() and GamePanel.repaint() every tick.

GameObject:
Has an x and y coordinate on the gamemap, and a width and height variable. speed variable is added to the x and y position every update
Has a state variable to determine what image should be loaded, and a hashmap with that state mapped to a list of images.


Player:
Subclass of GameObject to handle user input.

GOBuilder:
Builder class for GameObject 

GamePanel:
The game should have one panel in the window that draws GameObjects.
update() calls OppdaterPosisjon for all GameObjects.

GWindow:
Initializes and manages the window itself.

uiPanel:
custom panel for startmenu and pausemenu, calls game.startGame()from here.

GameMap:
Keeps track of all GameObjects in arrays, and a hashmap with integers representing the layers of objects mapped to arrays
with the objects. Methods addPlayer(player, layer) and addGameObject(GameObject, layer) for adding objects to the map.
Update() calls updatePosition for all objects
Also handles logic for the camera with cameraOffset variables and adjustCamera()


ImageLoader:
class with static methods to handle images, with scale(image, width, height) to scale the image to the correct size

AudioPlayer:
support class for playing audio files.