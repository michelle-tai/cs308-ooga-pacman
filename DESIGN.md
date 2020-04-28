# Team 9 + 11 = 21
### Members
Hannah Taubenfeld
Caleb Sanford
Olga Suchankova
Michelle Tai

### Roles
Hannah: Frontend/Player
* creating pacman and ghost views
* creating the mapview
* light and dark mode
* buttons for the interface
* combobox for choosing level 
* controller class
* everything frontend pretty much!

Olga: Backend/Engine
* collision manager
* collision logic using reflection
* editing movement 
* gamestep class
* creating hierarchy using dynamic and static sprite
* gamecontainer creator
* map graph to help with movement 
* text file parsing

Michelle: Generalist
* some collision manager planning/design
* integrating pacman movement based on key presses
* refactoring code to integrate data into player and engine
* creating the movement classes 
* package organization in engine
* exceptions
* level creator

Caleb: Data
* Collision file reader to help with reflection 
* level file reader
* level manager
* creating the file structure needed to add a new level or game
* xml parsing
* path manager
* also integrating data into the rest of the program


### Project's design goals
Our design goals were to make it easy to modify the Pac-Man game 
so that it can be customized however the user likes. For example, changing the game from single-player to multiplayer, changing the images, AI enemies, etc. As a result, new features that would be easy to add are more pacman using different keys to control each pacman, smarter vs dumber ghosts, infinite/torodial maze, etc. Additionally, the goal was to make the code as extendable as possible, which can be seen in both the front end and back end by creating different classes for individual components/objects of the game.

### High level design 
To accomplish our design goals, we first created a hierarchy of sprites within the backend so that new sprites/game characters can be easily added to the game. Every sprite extends either a DynamicSprite, which moves, or a StaticSprite, which doesn't. These two sprites imeplement the Sprite interface.
In the front-end, there are corresponding view classes for the backend sprites. For example, we have a PacMan in the backend, and a PacManView in the front end. To avoid tightly coupling the 2, we didn't have the frontend pacman have an instance of a backend pacman. Instead, we decided to have the sprites have IDs, and through the Controller class, the frontend sprites can update posisitions, statuses, etc through the controller getting information from the backend object with the same ID. This was also done for the ghosts and the coins.    
We created a Movement class, and DynamicSprites have an instance of a Movement class. This is so that we could try the composition design pattern, so that we delegated movement calculations (like new x and y positions) to the Movement class.
The Movement class's behavior is enabled through the MapGraphNode class. This is essentially a graph like data structure that informs the movement class where legal moves are made (ie no boundaries in the way).
We also created a CollisionHandler class that when 2 sprites and all the game data are inputed through use of an instance of the GameContainer class, uses a pre-loaded data structure to call appropriate methods using reflection based on the class and status of the sprite. 
The data interacts with the rest of the progam through the LevelManager, PathManger and Collision classes. The level manager creates new Level classes using reflection based on the level.txt files in the current game's level directory. The PathManger class is used by all parts on the code to get the correct paths to data in the current game. The Collision class reads a collision.xml file for the game and creates a data structure for the backend to use.
The InteractiveLevelCreator class opens up to a new scene in which the user can interactively create their own level files. When the user presses "Save," the class delegates the translation of the CustomImageViews (which were made so that we could get the image names) to the CreateLevel class so that a .txt file, which is what our program reads in to create our games, is created.   

### Assumptions and simplifications
* The user would have the classes/code written already if they wanted to have the game have different collision behaviors.
* The block numbers won't be too large (max around 40 x 40) so that the game is still playable on the screen
* The user creates a level that is _ x _ since certain dimensions seems to cause problems
* to create multiple games, you need to update the FilePaths.properties file to tell the game where all of the objects are located. 
* max 3 lives (as of now)
* map layout is static once loaded.
* The map layout is always assumed to be a grid.
* the user can only finish a level if they collect all coins  

### How to add new features

* To create a level progression, we would use the max score calculated in the Visualizer, and then when this max number is hit, the LevelManager needs to create a new GameContainer with the new level and replace that GameContainer with the old one in the Controller
* To implement multi-player, we need to add code into the data side so that multiple pacmen and their properties can be passed in. Each Pacman's keys that control it's movement are in a properties file, so we would either need to create multiple properties file for different pacman, or change our file to that it's in an XML (which might make it easier to read in a previously undertermined number of pacman and their keys)
* To implement more collision behvaviors, we would need to add a private method in the CollisionHandler and add that same method into the collision.xml file, making sure that every sprite/combination is covered?
* To add elements to a Level, we would need to create new methods in the Level class corresponding to the letters of the new elements in the txt file. Then, we would need to make sure that new elements have corresponding representation in the collisions xml file. In the backend, assuming that the objects can be created with a subset of variables already specified in one of the sprite classes, a user can specify a new type and its collision behavior will be described in the collisionhandler class. This is for example additional powerups. 
* to implement new ghost movement behavior, modify the private pickDirection method in the Movement class.
* To implement a dynamic map, the backend block class would need an ID for the front end to be able to update, and the GameStep Class would have to implement a function that updates all the block sprites at each game step.