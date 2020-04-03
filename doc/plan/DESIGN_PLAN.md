## DESIGN_PLAN

### Introduction

- Problem: Creating a classic game that consists of Pac-Man with different design variations.
- Design Goals: The Pac-Man game will be as abstract as possible in order to allow for more variation. So what is most flexible is probably the layout of the Pac-Man maze, the number of ghosts, how Pac-Man is controlled (which keys move him), etc.
- Architecture: Things like food items are open to extension since we want to allow others to be able to make different types if desired, but things like how Pac-Man calculates how to move (like 50 px to the left) are closed to modification. However, this behavior can possibly be open to extension. 

### Overview

- Engine:
    - Purpose:
        - framework of general classes to support any kind of game within the genre **
        - setting up, running, and completing levels or stages
        - rules, interactions, or events
        - goals, both small and large
        - progression through the game
        - behaviors or animations
        - game status information
        - game genre specific concepts
    - Interaction with others:
        - Load information from data
        - Provide relavent game status to player (lives left)
    - Classes/Data structures:
        - Main
        - Controller
        - Basic Pac-Man elements (interactions between PacMan elements)
        - Stores instantaneous game state

- Player:
    - Purpose:
        - program that loads the game data and uses the game engine to run a particular game **
        - see which games are available, including at least each game's name, image, and description
        - display dynamically updated game status information (i.e., heads up display (HUD))
        - replay the game repeatedly without quitting
        - switch games repeatedly without quitting
    - Interaction with others:
        - Receives information from the Engine
        - Recieves Javafx properties and display text from Data
    - Classes/Data structures: 
        - All front end elements
- Data:
    - Purpose:
        - classes that read configuration files, assets, and preferences to error check and assemble the correct combination of engine classes **
         - text displayed in the GUI (e.g., to localize it or display it in a different language)
        - graphical icons used in game (e.g., to turn a SciFi game into a political statement)
        - point values of game objectives (e.g., for tuning the game or to make a bonus level)
        - number of levels, their starting configurations, and the order in which they are played
        - keys used for interaction (e.g., to accommodate preferences, multiple players on the same keyboard, or using multiple controllers)
    - Interaction with others:
        - Sends JavaFx properties to Player 
        - Sends other game specific information to the engine
    - Classes/Data structures:
        - General file reader superclass
            - More specific file reader subclasses
        - Throws errors

lucidchart.com/documents/view/da83f38f-6c41-4928-82e4-b61ad472a099/0_0

### Design Details

- Engine
    - Extension:
        - AI controled players
        - Allowing level customization either through Data loaded file or Player gui 
        - Powerups
    - Resources: lists of rules and other game specifics from the data
    - Handling specific features: ability to create different games, Ability to control interactions between Pac-Man objects and handle their front end appearances (animation)
    - Collaboration with others: Load information from data, Provide relavent game status to player (lives left)
    - Justification: Does most of the backend and is able to store the important information. By doing so, all information is localized and easy to update.
- Player
    - Extension: Multiplayer views and keys to use, leaderboard, choosing preset preferences, dark mode, etc.
    - Resources: GUI information from Data module, other images used in the view from resource file
    - Handling specific features: able to set up all the GUI 
    - Collaboration with others: collects information from the data module and engine when required, and updates the engine when required
    - Justification: Simple design, want to separate the player's front end components from other elements of the design
- Data
    - Extension:
        - Save current game state into a loadable file
        - Multiple file types supported
    - Resources: Properties files, files saved to user's computer
    - Handling specific features: Is able to read files, organize information in files to send to other modules by bindings/observable objects
    - Collaboration with others: sends JavaFX information to the player and other game specific information to the enginer
    - Justification: Is highly extendable, can use general abstract classes and create other sub classes 

### Example games

**Goal: Employ composition in order to enable the creation of Pac-Man games with any combination of move mechanics, maps, etc**

1. Classic Pac-Man
    - classic rules of Pac-Man
    - no major altercations
    - a Pac-Man trying to eat all of the food
    - Ghosts trying to eat the Pac-Man
2. Gravity Pac-Man: 
    - movement is based on user selected angle of gravity
    - angle of map in GUI changes
3. Scrollable Pac-Man: 
    - map is infinate, when the player moves out of the screen boundaries, more map generates

### Design Considerations

- passing around information
    - bindings
        - pros: automatically information, can link two elements to be permanently connected
        - cons: have to unbind elements if any changes need to be made, harder to manipulate information
    - getter and setters
        - pro: can easily manipulate the information, can make information more public/private depending on preference
        - cons: need to call these methods every time you want the information, need to know when something changes
- responsibilities of player vs engine
    - front-end is in engine
        - pros: a lot of front-end information is stored in the engine which leads to less passing of information
        - cons: all modules and APIS are verey connected and harder to separate
    - front-end is in player
        - pros: a more distinct separation among modules, able to distinguish between data for general information and data for GUI specifics
        - cons: need more bindings/ getters and setters and more APIs to keep track of changing information
- data representation of game state
    - Array structure
        - pros: intuitive accessing of information
        - cons: Quite rigid implementation and representation of backend elements using backend properties (not flexible). Also requires more information than necessary to be stored at any given instance
    - Map structure
        - pros: flexible for future program extension (not rigid). Alows only relative information to be stored.
        - cons: less intuitive

- data file type
    - properties file
        - pros: easy to read and modify
        - cons: harder to represent complex data
    - xml file
        - pros: can store complex data
        - cons: hard to modify
    - both
        - best option