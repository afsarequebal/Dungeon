# About/Overview

The game of dungeon consists of a network of tunnels and caves that are interconnected so that player can explore the entire world by traveling from cave to cave through the tunnels that connect them. All caves can have one or more treasures. Tunnels will not have treasure. Dungeon will have treasure in 20 percent of the locations. Maze for the dungeon will be generated randomly. Also start and end point will be generated randomly.  Additionally, dungeon will have arrows in caves and tunnels and their percentage will be same as treasure. Dungeon will also have monsters in it. The number of monster will be provided in the command line arguments. Monsters can be at cave only. There should be atleast one monster in end point and start point cannot contain monster.
It is a text console based game. The player takes on the role of the protagonist in an interactive story driven by exploration and/or puzzle solving". In a text-based game, the way the game is played is through printable text data, usually through a console.
Player moves based on the available directions. Player can also pick up treasure or arrow. Player can also try to slay monster by throw arrow in any direction.

In this part of our project, we be implementing a graphical user interrace based controller  and view that can be used to play the game that we are working developing in our three-part MVC project. In addition, we will be adding enhancements to the dungeon to make the game more interactive and fun.

## List of features
1. Creation of a dungeon, which shows only the location explored by the player. All the unexplored location will be blank.
2. There of two panels. Dungeon panel containing the maze and display panel containing the location details and instruction to play the game.
3. In the menu, four options are given namely new game, restart, reuse and quit.
4. In the info panel, additionally quit button and cancel current action is provided. By clicking on quit button, maze closes. By clicking on cancel current operation, pickup or shoot is cancelled.
5. In the display panel, a button is present is get player details.
6. When My details is clicked, player description including number of diamond, sapphire, arrow, rubies are shown.
7. Player can move in the dungeon using keyboard and mouse.
8. Player can pick up treasure by clicking on P followed by A for arrow, D for diamond, E for emerald, R for ruby.
9. If a monster is location at a distance of a smell is felt and it grows as player move towards the monster.
10. If the player goes to monster location, player dies and loses the game.
11. To kill the monster, player can click on S followed direction given by arrow key and number of distance to throw arrow.
12. If the arrow reaches the monster, monster will be killed in two arrow throws.
12. If player reaches end location without getting killed, players wins else loses.
13. Player can sense a pit in nearby location and if player goes to that location, player dies and loses the game.
14. Player can run into thief, in which case, thief steals all the player treasure.
15. Player can run into roaming monster in which case player will have to fight by pressing the F button repeatedly. Display screen shows result of each step and randomly player dies or wins. 
16. For starting a new game, player needs to click on new game menu and provide maze configuration including number of rows, number of columns, interconnectivity, treasure percent and is wrapping.
17. If player clicks on restart game, maze is created with same configuration.
18. If player clicks on reuse game, same maze is loaded.
19. If the size of the maze is greater than the game panel size, player can see scrolling functionality.
20. Each time player starts the game, a new dungeon maze is created.
 
## How to run
DungeonGUIDriver in the default package is the class containing main method. This class is used to test functionalities of the project. 
1) To start the game in GUI mode, below command line input is required
	java -jar DungeonViewTest.jar
	Once the game is started user can be provide input for maze configurations.
2) To start the game in text-based console mode a command line input is required to run the jar. 
	A valid input will be have values including noOfRows, noOfColumns, interconnectivity, isWrapping, percentageOfTreasure,noOfOtyugh. An examples is 10 10 10 1 20 5. 
	So the command to run the jar is 
	java -jar DungeonViewTest.jar 10 10 10 1 20 5
	Here first 10 is number of rows, second 10 is number of columns, third 10 is interconnectivity, fourth 1 is for wrapping, fifth 20 is fro percentage of treasure and arrow, sixth 5 is for number of otyugh.

## How to Use the Program.
Move -
	1) Each location in the maze have possible direction image and  optionally contain treasure, arrow, thief , roaming monster.
	2) The maze loads with a blank image excluding the start location.
	3) Player can move to north, south, east, west location by clicking on the arrow key of keyboard or mouse click on adjacent location.
	4) Player can only move to adjacent location if there is path from current to next location, else nothing will happen.
	5) Once player moves to adjacent location, screen will show all the details of the already visited location and the current location.
	6) Additionally player image will appear in the current location.

## PickUp
	1) To pick a treasure, player needs click on P and game will ask for next input. 
		1.1) If player clicks D, diamond is picked
		1.2) If player clicks A, arrow is picked
		1.3) If player clicks E, emerald is picked
		1.4) If player click R, ruby is picked
		1.5) If location does not have any treasure or arrow, click of P will not work
## Shoot
	1) To shoot a monster, player will have to provide direction and distance
		1.1) First player clicks on S to start action.
		1.2) Then game will ask to give direction to shoot, then player can use arrow key to provide direction.
		1.3) After direction, game will ask to give distance the arrow should travel. It can be provided by clicking on 1-5 number key.
		Once shoot is successful, monster dies and player wins if end location is reached.

## Player Details
	To get the player description, player needs to click on my details button on screen.

## Location details
	Right panel on screen shows location details

## Menu
	Menu has four options
		1.1) New Game - on click of new game, game asks for maze configurations including row, column, interconnectivity, wrapping, treasure percent. After giving input and click on ok button, new maze is created.
		1.2) Restart, Reuse and quit button will restart the game with same configuration, restart with same maze and quit game respectively.

## Pit 
	if a pit is spotted in adjacent location, identified by pit.png image, and player goes to that location, game end and player dies.

## Thief
	Thief is identified by thief.png and thief will steal all player treasures.

## Roaming monster
	Roaming monster is identified by monster.png. When roaming monster is encountered, player can escape to adjacent location or choose to fight monster by clicking on F. After each click of F, player and monster will hit each other and player will be lose if player health is reduces to zero. If monster health is reduced to zero, then monster disappear.

## Scroll 
	Scrolling of the maze works, if the size of game is smaller than maze size.
	
## Cancel current action
	If player has already click P for pickup or S for shoot or arrow for shoot direction and number for shoot distance, current action can be cancelled by clicking on this button.
	

## Sample user input to be provided
Game can be simply started by running command line input. If player choose to play in different maze, player can use menu buttons to restart, reuse or start a new game.

## A sample run can be found in below files.
## 1) Run1BlankScreen.png - One run that shows maze starts with blank screen excluding start location and indicates pit is in adjacent location.
	1.1) When the game starts the blank screen appears excluding the start location which contains treasure, arrow.
	1.2) The image pit.png appears on the start location indicating pit is in adjacent location.
	1.3) Same information of pit in adjacent location appears on the right side of screen,
	
## 2) Run2Pit.png - One run that shows player dies if moves to a location containing pit.
	2.1) As per Run1Blank.png, pit is spotted in adjacent location.
	2.2) In the screen player moved to left side of the initial location and dies. The image for pit hole.png.
	2.3) Same information is show in the info(right) side of the game screen.

## 3) Run3Menu.png - One run showing the menu in the top left of screen.
	3.1) On click of New Game on top left, a menu appears. It contains new game, restart(starts game with same configuration but different maze), reuse(start game with same configurations) and quit(close the maze).

## 4) Run4PlayerDetails.png - One run that shows player details
	4.1) Once game start, if player clicks on My details, pop up comes showing player details including player arrow, diamond, emerald and ruby.

## 5) Run5PlayerLosing.png - One run that shows player losing the game
	5.1) Player starts at [5][3], after navigating through the dungeon, player reaches location [0][3]. As shown in screen, otyugh.png is shown, meaning player has reached otyugh location, without killing it, hence player loses
	5.2) Player losing the game is shown in information panel in right panel of screen.
	
## 6) Run6PlayerWinning.png - One run that shows player winning the game.
	6.1) As player navigates in the dungeon and throw arrow to kill otyugh, it reaches end position to win the game.

## 7) Run7Thief.png && Run8Thief.png - One run that shows player loses treasure to thief
	7.1) In the Run7Thief.png player has 1 diamond, 1 emerald, 1 ruby. As player moves to thief location[0][0], thief is identified and all players treasures are lost as shown in Run9Thief2.png

## 8) Run8InitialArrow.png - One run that shows player starts with zero diamond, zero emerald, zero ruby and three arrows.
	8.1) As show in image, player starts with three arrow as only explored location is start location.
	
## 9) Run10Move.png - One run showing moving player
	9.1) In the right side of screen, instruction is given to navigate the maze. Player can move by clicking on adjacent location or clicking on arrow key of keyboard.

## 10) Run11LocDetails1.png, Run12LocDetails2.png, Run13LocDetails3.png  - One run showing location details
	10.1) As shown in above images, right panel shows location details showing if that location has diamond, ruby, sapphire, arrow. It also if faint or big smell of monster is detected.

## 11) Run14Pickup1, Run15Pickup2, Run16Pickup3 - One run showing pickup details
	11.1) As shown in above images, click on P button is required to pick up arrow. Screen then updates to show that user needs to click A for arrow, D for diamond, E for emerald, R for ruby.
	11.2) Once user clicks on E,  sapphire from the location disappears and appears in player description.
	
## 17) Run17Shoot1, Run17Shoot2, Run17shoot3.png
	17.1) As shown in the images, right panel shows, instruction to shoot the monster. To shoot, press on S button, then click on arrow key for direction and number 1-5 for distance.
	17.2) Once monster is dead, player can move ot 
	
## 18) scrolling.png
	18.1) It shows scroll is active if size of maze is greater than board size.

## 19) Run18FullGame 
	19.1) It shows full game of the dungeon. After successfull navigation player reaches end and wins

## 20) Run20Menu
	20.1) It shows the menu items like row and columns to be asked by user creating new dungeon
	
## 21) Run21NewGame
	21.1) It shows new game once user input values
	
# 22) FightRoamingMonster
	22.1) It show to fight monster, user need to click on F button repeatedly. It shows user dies after fight.
	
# 23) FighRoamingMonster1
	22.2) It show player wins after fight.

The input used to generate it is 5 5 10 1 50 5
It includes functionalities of
	1.1) Printing the state of the game, including treasure and arrow players have, all the direction player can move to and all the treasure/arrow that can be found in current location.
	1.2) Sample run starts from position 1 and moves to position 8.
	1.3) After giving input M E M S M S M S M S M E M S  S 1 S M S, the player reaches end.
	1.4) After each action, output denotes the current state.
	1.5) Once player reaches end and since no monster is found, player wins and game ends.
	
## 2) DungeonRun2.txt - One run that shows the player killing a Otyugh 
The input used to generate it is 5 5 10 1 50 2
It includes functionalities of
	2.1) Printing the state of the game, including treasure and arrow players have, all the direction player can move to and all the treasure/arrow that can be found in current location.
	2.2) Sample run starts from position 1 and moves to position 1.
	2.3) After giving input S 1 N S 1 N, the player throws two arrow in the the south direction at a distance of 2.
	2.4) The state print, You hear a great howl, seems monster is dead. meaning otyugh is dead,
	2.5) In the next state, no smell is found as otyugh is dead.

## 3) DungeonRun3.txt - One run that shows the player being eaten by a Otyugh
The input used to generate it is 5 5 10 1 50 2
It includes functionalities of
	3.1) Printing the state of the game, including treasure and arrow players have, all the direction player can move to and all the treasure/arrow that can be found in current location.
	3.2) Player starts at location 5.
	3.3) After giving input in sequence M W M N M W M W M W, player ends up the location of the dungeon and hence loses the game.
	
## 4) DungeonRun4.txt - One run that shows the player picking up arrows
The input used to generate it is 5 5 10 1 50 2
It includes functionalities of
	4.1) Printing the state of the game, including treasure and arrow players have, all the direction player can move to and all the treasure/arrow that can be found in current location.
	4.2) Player starts at location 1.
	4.3) As shown in output, current location has 1 arrow.
	4.4) Player gives input as P ARROW, meaning pick an arrow
	4.5) Player bag is updated and also arrow is removed from the location.
	
## 5) DungeonRun5.txt - One run that shows the player picking up treasure
The input used to generate it is 5 5 10 1 50 2
It includes functionalities of
	5.1) Printing the state of the game, including treasure and arrow players have, all the direction player can move to and all the treasure/arrow that can be found in current location.
	5.2) Player starts at location 4.
	5.3) As shown in output, current location has 0 treasure. Hence moved to west using M W
	5.4) Player finds ruby, diamond, sapphire in current location, Hence player gives command, P RUBY P DIAMOND P SAPPHIRE
	5.5) Player bag is updated and also treasure is removed from the location.
	
## 6) DungeonRun6.txt -One run that shows the player navigating through the dungeon
The input used to generate it is 5 5 10 1 50 2
It includes functionalities of
	5.1) Printing the state of the game, including treasure and arrow players have, all the direction player can move to and all the treasure/arrow that can be found in current location.
	5.2) Player starts at location 10.
	5.3) Player give command as to navigate in the dungeon.
			
## Design/Model Changes.
I have modified my UML to include view classes to show the complete maze diagram. I have also added dungeon panel and option frame class to show the maze.

## Assumptions 
	1.1) I am assuming player can throw arrow in possible direction. If user throws an arrow in invalid direction, a error is throw saying arrow cannot be throw in given direction.
	1.2) Currently dungeon supports only one monster at a location.
	1.3) Currently it supports 1 arrow at a location.
	1.4) To determine if monster is half injured, i have defined strength variable. If strength is 2, it 	means monster has full health, 1 mean half health, zero means monster is dead. 
	1.5) A player can throw arrow and will be effective only if it is at a distance of 5 from the location it is thrown.
	1.6) Currently the roaming monster can be at only one play. 
	1.7) If the player goes to the location of pit, player dies.
	1.8) Currently thief will keep on stealing treasure from player and thief will not disappear from screen.

## Limitation 
	1.1) NA

##Cititation
NA