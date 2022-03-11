# About/Overview

The game of dungeon consists of a network of tunnels and caves that are interconnected so that player can explore the entire world by traveling from cave to cave through the tunnels that connect them. All caves can have one or more treasures. Tunnels will not have treasure. Dungeon will have treasure in 20 percent of the locations. Maze for the dungeon will be generated randomly. Also start and end point will be generated randomly. 

## List of features
1. Creation of a dungeon consists of caves and tunnels. To create a dungeon, we need start point and end point, number of rows, number of columns, interconnectivity, is wrapping, percentage of treasures.
2. Once a dungeon is created, player is initialized at the start point. Initially there will be no treasure available to the player.
3. Player will be able to move from one location to another.
4. If the location of the player has treasure, players has the chance to pick up treasure.
5. If the location doesn't contain any treasure, player will not be able to pick up treasures.
6. If player picks up a treasure, location will not have the treasure.
7. It players moves in the direction of a wall, then players should not be allowed to do it.

## How to run
DungeonDriver in the default package is the class containing main method. This class is used to test functionalities of the project. The command line input is required to run the jar. A valid input will be have values including noOfRows, noOfColumns, interconnectivity, isWrapping, percentageOfTreasure.
An examples is 10 10 0 0 20. 
So the command to run the jar is 
###
java -jar DungeonTest.jar 4 4 0 0 20

## How to Use the Program.
Program DungeonDriver is includes a series of test and prints the results. A sample run can be found in below files.

## Maze representation
Each location is represented as a number from zero to (noOfColums*noOfRows -1). In addition it includes directions and treasure at that position.
	For example, below is location 5, it is has path in down direction and treasures at this location are 
	rubies1, rubies6, sapphire0.
	{5(D, ) & (RBS1, RBS6, SAP0, ) }


## 1) DungeonRun1.txt - One run that shows a wrapping dungeon
Lines 64 to 94 in DungeonDriver.java is used to create output.
The input used to generate it is 10 10 0 1 10
It includes functionalities of
	1.1) Printing the maze to understand the paths in it. 	
	1.2) Sample run starts from position 36 and moves to position 85.
	1.3) After following path from 36 to 35, to 34, to 24, to 14, we reach at position 4. Next moves is up 		direction, which means player can move to position 94(last row) in the wrapping dungeon.
	1.4) From location 94, it moves down to location 4(top row).
	1.5) After a sequence of moves, players finally reaches destination 85
	1.6) Also in the maze, row zero has possible to last row, for eg up from location zero to location 90.
	
## 2) DungeonRun2.txt - One run that shows a non-wrapping dungeon
Lines 64 to 94 in DungeonDriver.java is used to create output.
The input used to generate it is 4 4 0 1 10
It includes functionalities of
	2.1) Printing the maze to understand the paths in it. 	
	2.2) Sample run starts from position 0 and moves to position 9.
	2.3) After following path from 0 to 1, to 5, to 1, to 2, we reach at position 2.
	2.5) After a sequence of moves, players finally reaches destination 9.
	2.6) Also in the maze, player cannot move from 1st row to last row.

## 3) DungeonRun3.txt - One run that shows a non-wrapping dungeon
Lines 108 to 116 in DungeonDriver.java is used to create output.
The input used to generate it is 6 6 0 0 10
It includes functionalities of
	3.1) Initial location of the player is 1.
	3.2) Since in non-wrapping, wrapping move is not allowed, so exception is thrown.

## 4) DungeonRun3.txt - One run that shows a wrapping dungeon
Lines 108 to 116 in DungeonDriver.java is used to create output.
The input used to generate it is 10 10 0 1 10
It includes functionalities of
	4.1) Initial location of the player is 1.
	4.2) Since in wrapping player moves to last row if upward direction is taken from first row,
	 	so after move, player comes at location 91.
	
## 5) DungeonRun4.txt - One run that shows the player visiting every location in the dungeon in wrapping dungeon
The input used to generate it is 10 10 0 1 10
Lines 119 t0 149 in DungeonDriver.java is used to create output.
	5.1) Once a maze is created, I am getting the maze representation and running a dfs algorithm to 		search for all location.
	5.2) At each location, I am printing players initial location, final location reached, treasures the 			players have, and the treasures at the cell location.
	5.3) I am also showing functionality of picking up treasure. If player picks up treasure, treasure at 		location is removed and players treasure is increased.
	5.4) Once all location are reached, program ends.
	
## 6) DungeonRun5.txt - One run that shows the player visiting every location in the dungeon in non- wrapping dungeon
The input used to generate it is 10 10 0 0 10
Lines 119 t0 149 in DungeonDriver.java is used to create output.
	6.1) Once a maze is created, I am getting the maze representation and running a dfs algorithm to 		search for all location.
	6.2) At each location, I am printing players initial location, final location reached, treasures the 			players have, and the treasures at the cell location.
	6.3) I am also showing functionality of picking up treasure. If player picks up treasure, treasure at 		location is removed and players treasure is increased.
	6.5) Once all location are reached, program ends.
	
## 7) DungeonRun6.txt - One run that shows the player starting at the start and reaching the end in wrapping dungeon
The input used to generate it is 10 10 0 1 10
Lines 119 t0 149 in DungeonDriver.java is used to create output.
	7.1) Once a maze is created, I am getting the maze representation and running a dfs algorithm to 		search for all location.
	7.2) At each location, I am printing players initial location, final location reached, treasures the 			players have, and the treasures at the cell location.
	7.3) I am also showing functionality of picking up treasure. If player picks up treasure, treasure at 		location is removed and players treasure is increased.
	7.4) Once all location are reached, program ends.
	
## 8) DungeonRun7.txt - One run that shows the player starting at the start and reaching the end in non- wrapping dungeon
The input used to generate it is 10 10 0 0 10
Lines 119 t0 149 in DungeonDriver.java is used to create output.
	8.1) Once a maze is created, I am getting the maze representation and running a dfs algorithm to 		search for all location.
	8.2) At each location, I am printing players initial location, final location reached, treasures the 			players have, and the treasures at the cell location.
	8.3) I am also showing functionality of picking up treasure. If player picks up treasure, treasure at 		location is removed and players treasure is increased.
	8.4) Once all location are reached, program ends.
	
## 9) DungeonRun8.txt - It shows the functionality of pickup treasure.
The input used to generate it is 10 10 0 0 10
Lines 119 t0 149 in DungeonDriver.java is used to create output.
	8.1) Players can pick up a treasure if treasure is available.
	8.2) Players will not be able to pick up treasure, if treasure are not available.
	
## Design/Model Changes.
I have modified my classes to make the method protected, so that external class cannot call internal classes. I have also added driver class. I have removed factory method to create players.

## Assumptions 
Maze is assumed to be represented in a two dimensional matrix. Each location treasure is a string value for time being, and can be enhanced later for added features. It is assumed that location with two paths are tunnel.

## Limitation 
	1.1) NA

##Cititation
NA