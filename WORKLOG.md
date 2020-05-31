# Work Log

## February 3rd 2020

- Setup github repo
- Created chess piece icons

## March 19th 2020

- Made our own board

## March 20th 2020

- Added the ability to click, but still needs multi-threading work
- Reworked chess piece icons

## March 24th 2020

- Added Start Menu to pick which operating system you are on
- Finished Multi-threading work

## March 25th 2020

- Removed the starting menu due to swing issues
- Added a click converter to switch raw frame coordinates to board coordinates
- Organized Github repo and split up methods into seperate classes for easier use

## March 26th 2020

- Added a tool bar and a message box to JFrame

## March 27th 2020

- Finally got a single piece on the board

## March 28th 2020

- Figured out a better, more efficant way of getting images on the board
- Added one piece to the board using the new method

## March 29th 2020

- Fully added all the pieces on the board
- Added movement to the piece on the board by converting click to a location where the piece moves to
- Added a setPiece() method which allows us to move images on the board by passing it 2 clicks
- Began adding piece legality

## March 30th 2020

- Fixed bug where taken pieces would move to (0,0)
- Added the ability to get all the possible legal moves for a rook based on current position
- Added a method to check for any teammates when stepping through the legal moves
- Added a method which gets all the possible knight legal moves based on current position

## April 7th 2020

- Added a python file that deletes .class files so we don't have to remove multiple manually

## April 11th 2020

- Added commenting to the knightLegalMoves() method

## April 23rd 2020

- Added a ChessTimer class
- Fixed a various amount of bugs
- Commented more methods

## April 25th 2020

- Created a method for generating pawn legal moves
- Added a method for generating king legal moves
- Began on creating a method for generating Rook legal moves
- Tested the new legalMove methods

# April 27th 2020

- Added Knight Legality
- Removed every single damn space from Rory's sorry excuse of an IDE
- Created a save class so games can be resumed

# April 29th 2020

- Fixed bug where only the white pieces were allowed to move
- Fixed the method that checks for out of bounds
- Added full legality for all the pieces

# May 5th 2020

- Updated the TODO file

# May 16th 2020

- Updated most legalMove methods and made them more efficant
- Fixed some bugs with movement
- Added a new Piece class with updated methods (does the samething as the old one just more efficent)
- Removed and organized imports
- Stopped using 2D arrays for coordinates and instead switched to an ArrayList of coordinates from the coordinate class
- Fixed more bugs with movement

# May 17th 2020

- Added GUI label changes
- WOAHHHHHHHHH GOT A 95% FULLY FINISHED GAME
- Fixed backend/frontend communication bug
- Re-enabled movement because it works now
- Added getEnemyTeam() method which returns a String 2D array with just the enemys and their locations
- Fully added movement and optimized it so the -1s no longer exist

# May 18th 2020

- Fixed some bugs because someone (not naming names) confused their rows and columns (hahaha oops)
- Made a getAllyTeam method
- Implemented saving

# May 19th 2020

- Added button actionListeners
- Cleaned up code and commenting 
- Deleted some useless files
- Fixed some movement bugs
- Begun developing check class

# May 20th 2020

- Finished Check class
- Began developing a checkMate class 
- Fixed more bugs and improved the UI design

# May 21st 2020

- Finised CheckMate class
