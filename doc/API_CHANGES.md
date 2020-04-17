###PacManView
public void move(Keycode key)
Added this so that can take user input and pass it to its backend pacman to tell it to move

handleKeyInput for the pacmanview 

move(String key) for updating the backend x and y values based on key press 

changeDirection 


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