### Player

#### Visualizer
public Scene setupScene()
- sets up a new scene whenever the start screen "start" button is clicked
 
public void addPacmen(int index, int row, int ID)
- adds an instance of a new pacman to the view every time one is found in the game container
 
public void addGhosts(int index, int row, int ID)
- adds an instance of a new ghost to the view every time one is found in the game container

public void addCoins(int index, int row, int ID)
- adds an instance of a new coin to the view every time one is found in the game container

public void pauseOrPlay()
- plays and pauses the animations based on the game status
 
public boolean getGameStatus()
- returns the game status in order to stop animations
 
public void setPacManSpeed(double speed)
- sets the speed of the animation depending on the pacman status

public void setGhostSpeed(double speed)
- sets the speed of the animation depending on the ghost status

public Scene getMyScene()
- returns the scene in order to change the CSS
 
public PacManView getCurrentPacMan()
- returns the current pacman in order to change the image

public void restartLevel()
- exits the game screen and brings back to the start screen

#### StartScreen
public Scene startScene()
- runs the stage to be set to the start home screen
 
#### PacManView
public void update()
- updates the pacman at every step

public SimpleIntegerProperty pacmanLives()
- gets the number of lives left from the pacman model

public SimpleIntegerProperty pacmanScore()
- gets the current score from the pacman model

public void handleKeyInput(KeyCode code)
- executes different commands for the pacman depending on the keyboard button clicked

public void choosePacMan(File imageFile) 
- chooses a new image for the pacman

public File getPacManImage(Stage stage) 
- returns the pacman image in order to choose a new one

#### CoinView
public void update()
- updates each coin and its status at every step

#### MapView
public Group createMap(GameContainer container)
- creates a map using the container in the backend

public Group getPacmen()
- returns the group of pacmen to be used in the frontend 

public Group getGhosts()
- returns the group of ghosts to be used in the frontend 

public Group getCoins()
- returns the group of coins to be used in the frontend 

public void changeGameStatus()
- changes the game status in order to display a "paused" message

#### GhostView

public void update()
- updates the ghost and its position and status at every step

#### NonUserInterface
public Node createComponents()
- creates all of the components for the non user interface side to be added to the view

public SimpleIntegerProperty getLivesLeft() 
- binds the lives to the backend so that it updates

public Label getScore()
- binds the score to the backend so that it updates

#### Styler

public Label createLabel
- creates a standard label 

public Button createButton
- creates a standard button 

public Button createButtonImage
- creates a standard button using only an image 

public Hyperlink createLink
- creates a standard hyperlink 

#### UserInterface

public Node createComponents()
- creates all the components to be added to the front ends


### Data

#### Collision
public Set<String> getActions(Integer status, String sprite1, String sprite2) 
- get all method names to call on a collision between the given sprites

public Map<Pair<String, String>, Set<String>> getCollisionRules(Integer status)
- get the Map containing the collision rules

#### Level
public Image getMapElementImage(int row, int col)
- get a map element image located at the given row and col

public Map<Pair<Integer,Integer>, Sprite> getModelMap()
- get the map containing the sprite locations

#### LevelManger
public Level getCurrentLevel()
- returns the Level object that is currently in use

public void setCurrentLevel(Integer level)
- sets the current level

public Level getLevel(Integer index)
- gets the current level

public Integer getNumberOfLevels()
- gets the total number of levels

#### PathManager
public static String getFilePath(String key)
- returns a filepath associated with the given key

public static String getGhostPath(Integer index)
- gets the ghost image path at the given index

public static String getPacManPath(Integer index)
- gets the PacMan image path at the given index

public static ResourceBundle getResourceBundle (String name)
- gets a ResourceBundle associated with the given name

public static String getProperty (String bundlePath, String key)
- gets a property from the given ResourceBundle