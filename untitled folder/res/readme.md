# About/Overview

The game of dungeon consists of a network of tunnels and caves that are interconnected so that player can explore the entire world by traveling from cave to cave through the tunnels that connect them. All caves can have one or more treasures. Tunnels will not have treasure. Dungeon will have treasure in 20 percent of the locations. Maze for the dungeon will be generated randomly. Also start and end point will be generated randomly.  Additionally, dungeon will have arrows in caves and tunnels and their percentage will be same as treasure. Dungeon will also have monsters in it. The number of monster will be provided in the command line arguments. Monsters can be at cave only. There should be atleast one monster in end point and start point cannot contain monster.
It is a text console based game. The player takes on the role of the protagonist in an interactive story driven by exploration and/or puzzle solving". In a text-based game, the way the game is played is through printable text data, usually through a console.
Player moves based on the available directions. Player can also pick up treasure or arrow. Player can also try to slay monster by throw arrow in any direction.

In this part of our project, we be implementing a simple text-based controller that can be used to play the game that we are working developing in our three-part MVC project. In addition, we will be adding enhancements to the dungeon to make the game more interactive and fun.

## List of features
1. Dungeon is already created as a part of project 3 implementation which contains treasures. 
2. Creation of monster and distributing them in maze. Checking end point contain monster and start point doesn't contain monster. 
3. Creation of arrow and distributing it in the same percentage as treasure.
4. Refactoring code to use controller to get user input and calling model methods based on user inputs.
5. Adding functionality of player being able to pick up arrow.
6. Adding functionality of player being able to throw an arrow.
7. Adding functionality of arrow being able to change direction in tunnel if it cannot travel in given direction.
8. Adding functionality of arrow hitting the monster only if given distance and direction takes the arrow to the monster location.
9. Adding functionality of monster health is decreased to zero if monster is hit twice.
9. Adding functionality of monster health is decreased to half if monster is hit once.
10. Adding functionality where if player reaches end, it wins only if monster is dead.
11. Adding functionality where if player reached end, it dies if monster health is full.

## How to run
DungeonConsoleDriver in the default package is the class containing main method. This class is used to test functionalities of the project. The command line input is required to run the jar. A valid input will be have values including noOfRows, noOfColumns, interconnectivity, isWrapping, percentageOfTreasure,noOfOtyugh.
An examples is 10 10 10 1 20 5. 
So the command to run the jar is 
java -jar DungeonTest.jar 10 10 10 1 20 5
Here first 10 is number of rows, second 10 is number of columns, third 10 is interconnectivity, fourth 1 is for wrapping, fifth 20 is fro percentage of treasure and arrow, sixth 5 is for number of otyugh.

## How to Use the Program.
Program DungeonConsoleDriver suggest user the direction user can move to, all the treasure and arrows player have, all treasure and arrow at the given maze location, and if some monster is found in nearby cells. 
The description(state) of the game at each point is a below
You have Arrow, Arrow, Arrow - It denotes that player have three arrows in the bag.
You have RUBY - It denotes that player has ruby in the bag.
You smell monster in nearby cell - It denotes that player found a monster in the adjacent cell.
You found faint smell of monster - It denotes that player is at a distance of two.
You are in a tunnel - It denotes that player is in a tunnel.
You are in a cave - It denotes that player is in a cave.
Doors lead to the S,  N, E, W - It denotes that player can move in south, north, east and west directions.
You find 1 arrow here - It denotes that an arrow is found at the current location of the player.
You find RUBY, RUBY, RUBY here - It denotes that the current location has 3 rubies.
Move, Pickup, or Shoot (M-P-S)?  - It denotes that player can move, pickup or shoot. It asks user to provide M,P,S for corresponding action to take place.
No. of caves (1-5)? - It asks user to choose the distance arrow should travel.
Where to? - It asks user the direction in which to throw arrow.
You hear a slight howl - It denotes that monster is half injured.
You hear a great howl, seems monster is dead. - It denotes that monster is dead.
You are out of arrows. Explore to find more - It denotes that player does n't have arrow left.

## Sample user input to be provided
M E M S M S M S M S M E M S  S 1 S M S 
Player has three options - M, P, S
	1.1) M S denotes action to move, followed by direction to move to.
	1.2) P ARROW denotes pick up treasure followed by treasure to be picked up, which is arrow
	1.3) S 1 S denotes throw a arrow at a distance of one in the south direction.

## A sample run can be found in below files.
## 1) DungeonRun1.txt - One run that shows the player winning the game by reaching the end
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
I have modified my factory classes to use only one factory class to generate treasures. I have also modified initial design, where model is containing a reference variable of grid type.

## Assumptions 
	1.1) I am assuming player can throw arrow in possible direction. If user throws an arrow in invalid direction, a error is throw saying arrow cannot be throw in given direction.
	1.2) Currently dungeon supports only one monster at a location.
	1.3) Currently it supports 1 arrow at a location.
	1.4) To determine if monster is half injured, i have defined strength variable. If strength is 2, it 	means monster has full health, 1 mean half health, zero means monster is dead. 
	1.5) A player can throw arrow and will be effective only if it is at a distance of 5 from the location it is thrown.

## Limitation 
	1.1) NA

##Cititation
NA