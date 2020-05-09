# Name Score
This a command line application that will compute the score for a list of first names.

Name Score takes the full path as a command line argument to a file that contains a list of first
 names. The supplied file must contain only a single line of quoted comma-seperated names. An
  example file would contain the following list of names:
```text
"MARY","PATRICIA","LINDA","BARBARA","VINCENZO","SHON","LYNWOOD","JERE","HAI"
```
A full sample file called names.txt is supplied in the base directory of this project. 

## Assumptions
* Duplicate names are allowed and will count toward the final score.
* All names will be converted to uppercase to ensure consistent sorting.
* Only uppercase Latin Unicode characters are counted in the score.

## Scoring A Name
To score the list of names, it will be sorted alphabetically and it will return the sum of the
 individual names. To score a name, sum the alphabetical value of each letter (A=1, B=2, C=3
 , ..., Z=26) and then multiply the sum by the name's position in the list (1-based).
 
For example, when the sample data above is sorted into alphabetical order, LINDA, which is worth 
12 + 9 + 14 + 4 + 1 = 40, is the 4th name in the list. So, LINDA would obtain a score of 40 x 4 = 160. 

The correct score for the entire list is 3194. 
The correct score for the names.txt file is 871198282.

## Required Software
1.  [Gradle](https://gradle.org/install/)

## Building with Gradle
To start the application:
```shell script
./gradlew run --args='/fully/qualified/path/to/name/file.txt'
```
If no file path argument is given, then the Name Score will run using the project's [names.txt
](https://github.com/jeremymatthewmiller/name-score/blob/master/src/main/resources/names.txt) file.
 
To run the unit tests:
```shell script
./gradlew test
```
