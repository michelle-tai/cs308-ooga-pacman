## USE_CASES

### Hannah Taubenfeld (hbt3)
1. The user wants to change the image used for the pac-man
    - The user clicks on the settings button in the corner of the screen
    - A new scene is created called the settings window
    - The user clicks the button labeled "Pac-Man image"
    - A file chooser appears and the user clicks their desired image (png or gif)
    - Once the okay button is clicked, the new image is sent to a setter method in the front end Pac-Man object and updates the image
    - This image is adjusted in size and is put in the position where the previous image was
2. Two users want to play the game
    - At the beginning of the game, the welcome menu scene is created
    - This scene has two buttons "Single Player" "Multiple Players"
    - The user clicks the multiple player option
    - Immediately, two instances of the Pac-Man class/object are created both in the front end and the back end
        - Within these classes in the back end, there are stored default keys that correspond to each separate instance of a Pac-Man
        - Within these classes in the front end, the two instances of the Pac-Man are nearly identical with the exception of their starting location
        - The Pac-Man class in the back end keeps track of their positions separately and helps to update the view
3. A user want to define their own keys to be used as controls
    - The user clicks on the settings button in the corner of the screen
    - A new scene is created called the settings window
    - Under the label "Player 1" there are 4 input boxes labeled left, right, up, and down. The input to these boxes is a key.
    - The user inputs their desired keys and clicks submit
    - In the back end, the engine updates the keys associated with the pac-man instance
    - In the front end, the pac-man instance updates the key input command to have the same visual functionality as before
4. The user wants to check their high score
    - The user clicks the "user information" button in the corner of the screen
    - A new scene is created that holds a list of user information, one being a list of high scores
    - This list is an array of previous high scores that is stored in Data and displayed in Player
    - Every time a user saves information, this list is saved too and therefore uploaded later
    - Once a user completes a game or loses a game, their score is added to the high scores list in order
5. The user is unable to currently finish a game and wants to continue later. They save their game and their current status.
    - The user clicks the "save game" button in the corner of the screen
    - A file chooser appears to name the package and choose a location for the package
    - The Data module saves the following information into text files and XML files and packages them together 
        - The position of the Pac-Man
        - The position of each ghost 
        - The layout of the maze (including which elements have not been eaten yet and still remain)
        - The number of lives left
        - The list of high scores
6. The user wants to load a game that they were previously in the middle of
    - At the beginning of the game, the welcome menu scene is created
    - This scene has another button "load game"
    - The user clicks this button and a file chooser appears
    - The user navigates and chooses their file of choice
    - The Data module reads in all the information from this package of text files and XML files 
    - From the text files it reads in the layout of the maze and which items still remain and have no been eaten
    - From the XML files it reads in the position of the Pac-Man, the position of the ghosts, the number of lives left, and the list of high scores
7. The user is playing late at night and wants to change the settings to a dark mode
    - - The user clicks on the settings button in the corner of the screen
    - A new scene is created called the settings window
    - The user scrolls through this window and clicks "dark mode"
    - Once this button is changed, a new CSS file is loaded
    - This accounts for the background color, the color of the images on the screen, and the text colors
8. The user wants to change the image used for the ghosts
     - The user clicks on the settings button in the corner of the screen
    - A new scene is created called the settings window
    - The user clicks the button labeled "Ghost image"
    - A file chooser appears and the user clicks their desired image (png or gif)
    - Once the okay button is clicked, the new image is sent to a setter method in the front end ghost objects and updates the image of each ghost
    - This image is adjusted in size and is put in the position where the previous image was 


### Caleb Sanford (cis12)
- The map for a Pac-Man game is described in a xml file and loaded when the program is started.
- The GUI text needs to be expanded to a different language, so all of the text fields are described in a properties file.
- A different Pac-Man graphic is chosen to be used in the game.
- All of the point values for the game can be changed from a properties file.
- The number of levels in the game can be specified by a level folder, containing configuration files for each level.
- Number of players changes, and the players want to use different keys to control Pac-Man.


- The type of movement that controls Pac-Man changes from up/down/left/right to an angle of gravity.
- The screen changes to a scrolling style game.

### Olga Suchankova (os33)

- User starts a game with specified game preferences (boundaries, movement rules)
	- The engine retrieves data from the Data module.
	- Data is used to generate game map based on data and boundary preferences
	- Engine populates map with game objects  based on preferences 
	- Game loop is activated by a listener to user input to start the level
	- Game loop calls step function
- PacMan and ghost objects collide with each other or other in game objects
	- After being moved to a new location, an updateStatus(Location Object) method is called which has access to information about other players, objects, powerups, coins, in the vicinity
	- updateStatus() changes the status of the current object, and other objects as specified by game rules
	- Collision game rules are loaded from data file and are assembled using composition


- PacMan and other movable objects need their locations updated for each timestep
	- Most recent user input is used to calculate the next state location (x,y) for each movable object.
	- move() method then assigns new X, Y coordinates

- Game Level is completed
	- Using data provided by the data module, the engine decides if that was the final level or not. If so Player object is updated accordingly (high score etc)
	- If not, the next level is generated using Data provided by the data module.


- Provide player class with the display of lives left in the form of bindings.
	- Frontend element that contains the number of player lives contains a listener which converts the lives left to a javafx element to be displayed in an hbox


- Ghost and Pac-Man object needs to respawn when “killed” 
    - Any properties that are needed to be reset or updated are (number of lives power up status, appearances)

- Pac-Man runs out of lives
    - Update player status (game over, new score)
    - End game loop
    - Show Game Over Screen that allows for navigation to other in game scenes

- Pac-Man moves past current screen boundary in scrollable / infinite map type
     - Map data structure holds current game displays and their static data as value with coordinate pair as a key.
    - New map is generated based on data provided in the data module and is added to map data structure
    - When the Pac-Man moves back to the map location that was previously explored, it is loaded from map data structure, instead of generating a new map.

### Michelle Tai (mrt36)
1. Make the enemies have controllable movement
    - Create a new "Movement" class and implement the methods to in a way so that the movement can be controlled
    - Place this instance of the Movement class into an Enemy object
2. Pac-Man collides with enemy without power up
    - Each sprite's "collidesWith()" method is invoked so that the appropriate behavior, taken from each sprite's CollisionsController, is deployed
    - Pac-Man loses a life, so decrement life count by 1
    - Maybe Pac-Man changes state to invincible for a moment to reset
    - enemy is unfazed, nothing happens to enemy
3. Pac-Man collides  WITH power up
     - Each sprite's "collidesWith()" method is invoked so that the appropriate behavior, taken from each sprite's CollisionsController, is deployed
    - Nothing changes with Pac-Man
    - Enemies become ghosts, in a vulnerable state, and change their image, for a set amount of time
4. Pac-Man collides with coin/pellet
    - Each sprite's "collidesWith()" method is invoked so that the appropriate behavior, taken from each sprite's CollisionsController, is deployed
    - Pac-Man's score increases by adding to the score counter
    - Coin/Pellet disappears
    - Pac-Man remains in current state
5. Pac-Man collides with enemy DURING power up phase
    - Each sprite's "collidesWith()" method is invoked so that the appropriate behavior, taken from each sprite's CollisionsController, is deployed
        - maybe collision controller is tied with the state instead?
    - Pac-Man's score increases by a set amount
    - The ghost disappears and later respawns
    - Pac-Man continues on in current state
6. We have multiple Pac-Man players 
    - Enter each pac-man's configuration (arrow keys that control it, type of movement, etc) in a file
    - Pac-Man score counter on the side counts the number of pac-men and bind properties appropriate to display the score OR creates appropriate number of score spots for each Pac-Man
    - depending on which pac-man eats what, scores will be updated appropriately
7. User pauses the game by pressing the spacebar
    - All sprites are frozen by not updating x and y position 
    - a new box appears on side of view to allow users to have the option to change the image of the Pac-men or enemies while paused
    - if decided to change, a file chooser dialog opens and the new image is loaded
8. User wants to restart
    - Pause the game by pressing the spacebar
    - press the restart button
    - reloads the game from the last entered game file
