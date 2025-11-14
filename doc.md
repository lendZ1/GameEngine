# GameObject

Purpose
- Represents a single rectangular entity in the game world. Handles position, movement, rendering and basic collision behavior.

Key fields
- layerObjects: ArrayList<GameObject> — references other objects on the same layer (used for collision checks).
- layer: int — the drawing/collision layer index.
- xpos, ypos: int — current top-left position.
- xspeed, yspeed: int — current velocity applied to position each update.
- width, height: int — size of the object (pixels).
- color: Color — fill color used when no image is set.
- image: BufferedImage — optional sprite; if present should be used to draw and to set object size.
- bounce: boolean — if true, object inverts speed on collision; if false, speed is set to 0.
- movable: boolean — whether other objects can push/move this object.
- gamemap: static GameMap — reference to the map/bounds for boundary checks.

Constructor
- GameObject(int xpos, int ypos, int height, int width, int xspeed, int yspeed, Color color, int layer, boolean movable)
  - Initializes position, size, velocity, color, layer and movable flag.

Important methods
- setLayerObjects(ArrayList<GameObject> layerObjects)
  - Provide the list of objects in the same layer prior to collision checks.

- increaseXspeed(int), increaseYspeed(int), setSpeed(int, int)
  - Modify current velocity.

- updatePosition()
  - Apply current velocity to xpos/ypos. Called by the game update loop.

- checkCollission()
  - High-level collision handler. Calls horizontal and vertical collision checks and applies bounce/stop behavior.

- collissionHorisontal() / collissionVertical() (private)
  - Detect collisions with world bounds and with other objects in layerObjects.
  - On object collisions, registerCollision() is called.
  - Collision logic considers the movable flag: collisions with immovable objects prevent movement; movable objects behave normally (can be pushed/handled elsewhere).

- setBounce(boolean)
  - Toggle bounce behavior on collision.

- registerCollision()
  - Hook for collision side-effects (score, sound, custom effects). Override or modify to implement specific reactions.

- draw(Graphics g)
  - Renders the object. Current implementation draws a filled rectangle using color; swap to draw image when sprite support is added.

Behavior notes and extension points
- Boundary handling: collisions with map edges call registerCollision and block/reflect movement.
- Pushing mechanics: the class exposes isMovable(); pushing logic and force/weight handling should be implemented in higher-level code (e.g., GamePanel or Map) or by extending GameObject with physics properties (mass, friction, velocity accumulation).
- Sprite support: when adding sprites, ensure the object's width/height are kept in sync with image dimensions.
- Layer management: layerObjects must be set for accurate collision checks; the GameMap or GamePanel should populate these lists each frame or when objects change layers.




# Player

Purpose
- Represents the player-controlled entity. Extends GameObject and overrides movement, collision resolution and rendering to provide responsive player control.

Key fields
- speed: int — base movement speed applied when input is active.
- forward, backward, up, down: boolean — input flags controlled by GamePanel/input handler.
- collisionDistance: int — computed corrective offset to avoid overlap while eliminating gaps when movement is blocked.

Constructor
- Player(int xpos, int ypos, int height, int width, int speed, Color color, int layer)
  - Calls GameObject constructor with player set as non-movable.
  - Disables bounce behavior for precise movement.
  - Loads a placeholder sprite (image) and uses it for drawing.

Important methods
- forward(boolean), backward(boolean), up(boolean), down(boolean)
  - Input setters invoked by the input system.

- updatePosition() (overrides)
  - Computes intended nextX and nextY based on current input.
  - Applies diagonal movement adjustment so diagonal speed feels consistent with orthogonal movement.
  - Uses separated-axis movement: tests horizontal movement first then vertical (or vice versa) to allow smooth sliding along walls.
  - When a collision would occur, uses collisionDistance from collisionAt(...) to nudge the player to the nearest non-overlapping position instead of allowing an overlap or leaving a gap.

- draw(Graphics g) (overrides)
  - Draws the player's image scaled to width/height.

- collisionAt(int testX, int testY) (private)
  - Performs boundary checks against gamemap and per-object overlap checks using layerObjects.
  - When an overlap is detected calculates the smallest penetration distance on the four sides (left/right/top/bottom) and stores that as collisionDistance.
  - Returns true if moving to (testX,testY) would collide; false otherwise.

Behavior notes and usage
- Player is created as non-movable (isMovable() == false) — it will not be pushed by other objects.
- The GamePanel or Map must set layerObjects for accurate collision detection.
- Separate-axis movement and collisionDistance handling remove small gaps and prevent tunneling for typical speeds.
- Diagonal speed uses sqrt(speed*speed/2) — consider using floating point arithmetic if precision issues appear.

