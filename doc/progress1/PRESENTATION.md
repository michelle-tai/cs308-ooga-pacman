## What genre or game are you focusing on and what commonalities and differences have you identified?
#### Classic Video Games: Pac-Man
#### Commonalities
* Main players
* enemies
* maze-like features
* collectables
* scorekeeping
#### Differences
* movement
* types of collectables
* types/number of enemies
* point value of things

## Who is expected to work on which parts of the project?
* Caleb: Data
* Hannah: Player/Frontend
* Olga: Engine/Backend
* Michelle: Both Player/Frontend and Engine/Backend

## What extensions you are expecting to work on?
* Dark mode
* High score
* Preferences: images for pacman and stuff
* Starting configs?
* “Artificial Player”

## What features you expect to complete during each of the three Sprints?
#### Sprint 1
* Basic interface working of the maze of pacman and a pacman moving around with the collisions for the two working
* be able to read maze configuration from data file
* Main character and block collision 
* Basic movement controls
* Configuration for maze
* Creation of sprites and coins, not necessarily integrated yet

#### Sprint 2
* Add in other sprites like ghost and collectables, figure out collisions for them
* modify maze configuration files to be able to place characters or have them in random places
* Other sprite collisions, integrate into game
* More configuration files for maze
* Read in number and behavior of sprites through files?
* Lives and score
* Start level progression

#### Complete
* Splash screen
* Upload map, players, and enemies
* Dark and light mode
* Level progression
* Preferences (like images)
* Create examples of different games 
* Restart button
* Default files

## Design and architecture goals?
The Pac-Man game will be as abstract as possible in order to allow for more variation. 
So what is most flexible is probably the layout of the Pac-Man maze, the number of ghosts, 
how Pac-Man is controlled (which keys move him), etc, point values of things.
  
Things like food items and sprites are open to extension since we want to allow others to be
able to make different types if desired, but things like how Pac-Man calculates how to move
(like 50 px to the left) are closed to modification. However, this behavior can possibly 
be open to extension.

#### Flexible/Open
* Movement
* Number of sprites and type of sprites
* Maze configuration 
* Collectable types
* Points values
* Collision behavior

#### Fixed/Closed
* Exact implementation of things?

## Modules
#### Engine/Backend
* Sprite behavior
* Collision behavior - changes view of things sometimes in the frontend/player
* Gives updated x y coordinates to frontend/player
* Accepts map object and other sprite configuration information 

#### Data
* Giving configuration info to engine, like map or level objects

#### Player/Frontend
* Change images, move character, rerender based on engine data
* make view of the map/level based on the data given from configuration flies






