final
====

This project implements a player for multiple related games. In particular, we chose to focus on Pac-Man, 
which is under the "Classic Arcade Games" category. 

Names: Caleb Sanford, Olga Suchankova, Hannah Taubenfeld, Michelle Tai


### Timeline

Start Date: 04/02/2020

Finish Date: 

Hours Spent: 120 hours total

### Primary Roles
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

Caleb: Data
* Collision file reader to help with reflection 
* level file reader
* creating the file structure needed to add a new level or game
* xml parsing
* also integrating data into the rest of the program


* Stack Overflow
* Java documentation 
* Google
* Our TA, Jack Proudfoot
* Lecture videos


### Running the Program

Main class: Main.java

Data files needed: 
within a game folder, you need a
* collisions.xml
* DarkStyling.css
* LightStyling.css
* StartStyling.css
* Ghost images (png or jpg)
* EnglishButtons.properties
* .txt level files
* images for the map
* pacman image
* ErrorMessages.properties
* FilePaths.properties
* GameProperties.properties
* GameRules.txt

Features implemented:
* levels
* game statuses
* loading in different levels without exiting game
* changing image of pacman midgame
* dark and light mode
* score
* lives
* pause
* restart
* custom collisions read in using reflection 
* different ghost movements
* everything set using files/properties


### Notes/Assumptions

Assumptions or Simplifications:
* staying within a pac-man-like game
* collision classes must be already created in the program before calling them in your
custom collision file
* user will enter a map that is enclosed by blocks on all 4 sides
* user will need to create new game files in the exact same structure as the deafultPacMan
* not too many pacman will be put into the game (since not enough keys to move infinite number of pacman technically
)
* only sprites right now are pacman, ghost, coins and blocks

Interesting data files:
* our level files and games

Known Bugs: 
* some non-deafult level games throws an error for some reason
* sometimes the different coins don't show up correctly
* when pacman powers up and collides with ghost, pacman still dies

Extra credit:
* light and dark styles
* AI (differing types of movement for ghosts)
* preferences
* interactive level editor


### Impressions
* This assignment was interesting since we got to apply everything we learned throughout the 
semester to a single project. 
* It was a bit tedious and stressful to integrate everything toward the end
* Testing was interesting to learn, but we found it hard to apply for our project?


