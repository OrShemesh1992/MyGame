# MyGame
In this project we have developed a system that enables the collection of geographic
information and the presentation the information in graphical tools.

The goal of the game is to eat all the fruits in the shortest time possible,
each packman has a speed at which he can advance to the next fruit,
and an eating radius that defines the minimum required proximity to the fruit he want to eat.
The game configuration will be determined by the information given in a predetermined
CSV file or file that we have created. We have created a menu for the user that will
allow him to create his own game by placing the packman, fruits, boxes, ghosts and the player on the map,
and setting the radius and speed for the packman , and export the game to a csv file,
in addition the menu allows him to upload an existing file of the game and run the game.
There are 2 options to start the game:
option one is the automatic game, there you need to choose the game you want to play and press 
"play", the algorithm will calculate the shortest path to eat all the fruits and the  packmans.
option two is to play by yourself by moving the mouse on the screen to the fruit or fckman to which you want to go.

## Authors
Or Shemesh & Yaara Goldenberg. 

## Tutorial
To start the program Import from your computer a csv file that containing the game data
or create your own game by selecting the fruit and packman and positioning them on the map.
Afterwards press the "start" button to start the game.
### Example of the automatic game:
![20190110_115121](https://user-images.githubusercontent.com/44780654/50961790-56e3c980-14d1-11e9-8bc4-d439e85bf96d.gif)
## Description the packages and classes
### Package:
#### Coords:
- MyCoords- 
In this class we created a number of functions such as calculate distance, with which we can do several calculations between vectors like, convert a 3D vector to polar coordinates, convert a polar coordinate to 3D point, adds two vectors together, distance, calculate the vector in meters and calculate the distance, azimuth and elevation.
- Csv2Kml-
In this class we read a csv file from our computer and convert it to a kml file.
- MultiCSV-
In this class we are looking recursively for a csv file.
#### GIS:
- Data-
In this class we describe each layer and its time.
- Element-
This class represents the parameters: mac, ssid, authmode, time and GPS coordinates with which we can perform additional calculations on vectors.
- Layer-
In this class we have a collection of elements that we read from a csv file, with which we could create a kml file.
- Project-
In this class we use the class Layer to receive multiple layers so we could a kml file.
#### PackmanGame:
- Fruit-
This class represents target (the target cannot move), each target (fruit) has an ID number, weight, map location (in meters or pixel) and string type.
- Packman- 
This class represents packman (the packman capable of movement), each packman has an ID number, speed, radius, map location (in meters or pixel) and string type. 
- Box-
This calss represents a box, each box is defined by two points.
- Ghost-
This class represents a gohst, each ghost has an ID number, spees, radius and its location on the map.
- Player-
This class represents the player, each game we have only one player. The player has a a start point and has
the ability to move by moving the mouse on the screen.  
- Game- 
In this class we have a list of packman and a list of fruits. This class has the ability to be built from the data it receives from the csv file, and also to create a csv file with the data it receives from a new game we created.
-  Map- 
This class represent the map which we use in the game. It included the map image, the fruit image and the packman image.
The class calculates conversion of coordinates from global representation to pixel and vice versa, and also calculates the distance in meters between 2 pixel points.
- MyFrame-  
This class is a graphical class that displays the packman and fruits on the map, showing the algorithm operation we created in the ShortestPathAlgo class, and also performing a recovery of data from csv files or creating a game by selecting the fruit and packman and positioning them on the map.
-Algorithm-
This class contains all the calculations so that the algorithm will calculate the shortest and best path in order to get as many points as possible in the game.
