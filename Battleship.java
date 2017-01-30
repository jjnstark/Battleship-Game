/*
 * Samuel Gosselin and John Stark
 * COMP 249
 * Assignment #1
 * February 1, 2017
*/

import java.util.Scanner;

/**
* Defines the functions of the Battleship game, including creating players,
* creating array of positions, setting ships and grenades, printing board,
* and determining winner.
* @author Samuel Gosselin
* @author John Stark
* @version 2.00
**/
public class Battleship {

	// Instance variables
	private Scanner scan = new Scanner (System.in);
	private Player p1 = new Player("Human"); // H player
	private Player p2 = new Player(); // computer player
	private Position[][] grid; // Array of position objects
	private char[][] printGrid; // Array of chars for printing grid

	/////////////////////////////////////////////////////////////////

	// Constructors

	/**
	* Default constructor for Battleship grid
	**/
	public Battleship()
	{
		grid = new Position[8][8];
		printGrid = new char[8][8];

	}

	/////////////////////////////////////////////////////////////////

	// isAvailable methods

	/**
	* Checks whether a position in the grid is already occupied, using numeric coordinates.
	* @param row	the row of the grid to be checked
	* @param col	the column of the grid to be checked
	* @return	whether the specified position is occupied
	**/
	public boolean isAvailable(int row, int col) // if row and column is known
	{
		return grid[row][col]==null;
	}

	/**
	* Checks whether a position in the grid is available using alphanumeric coordinate notation.
	* @param coordinate	the alphanumeric coordinate entered by a player, corresponding to a column given by a letter and row given by a number.
	* @return whether the specified position is occupied
	**/
	public boolean isAvailable(String coordinate)
	{
		int row=0,col=0;

		// Checks the character at the beginning of the alphanumeric string
		switch(coordinate.charAt(0))
		{
		case 'A' : col=0;break;
		case 'B' : col=1;break;
		case 'C' : col=2;break;
		case 'D' : col=3;break;
		case 'E' : col=4;break;
		case 'F' : col=5;break;
		case 'G' : col=6;break;
		case 'H' : col=7;break;
		}

		// Checks the number at the end of the alphanumeric string
		switch(coordinate.charAt(1))
		{
		case '1' : row=0;break;
		case '2' : row=1;break;
		case '3' : row=2;break;
		case '4' : row=3;break;
		case '5' : row=4;break;
		case '6' : row=5;break;
		case '7' : row=6;break;
		case '8' : row=7;break;
		}

		// Returns true if the given coordinate is occupied
		return grid[row][col]==null;

	}

	/////////////////////////////////////////////////////////////////

	// isWithin methods

	/**
	* Checks whether a position is within the grid
	* @param row	the row of the grid to be checked
	* @param col	the column of the grid to be checked
	* @return	whether the specified position is within the grid
	**/
	public boolean isWithin(int row, int col)
	{
		// Checks if the row and position coordinates are within the grid
		return row>=0 && row<=7 && col>=0 && col<=7;
	}

	/**
	* Checks whether a given coordinate is within the grid
	* @param coordinate	the alphanumeric coordinate entered by a player, corresponding to a column given by a letter and row given by a number.
	* @return whether the specified position is within the grid
	**/
	public boolean isWithin(String coordinate)
	{
		int row=0,col=0;

		// Checks the character at the beginning of the alphanumeric string; if not within grid, gives default value outside grid
		switch(coordinate.charAt(0))
		{
		case 'A' : col=0;break;
		case 'B' : col=1;break;
		case 'C' : col=2;break;
		case 'D' : col=3;break;
		case 'E' : col=4;break;
		case 'F' : col=5;break;
		case 'G' : col=6;break;
		case 'H' : col=7;break;
		default : col=99;
		}

		// Checks the number at the end of the alphanumeric string; if not within grid, gives default value outside grid
		switch(coordinate.charAt(1))
		{
		case '1' : row=0;break;
		case '2' : row=1;break;
		case '3' : row=2;break;
		case '4' : row=3;break;
		case '5' : row=4;break;
		case '6' : row=5;break;
		case '7' : row=6;break;
		case '8' : row=7;break;
		default :row=99;
		}

		// Checks if the row and position coordinates are within the grid
		return row>=0 && row<=7 && col>=0 && col<=7;
	}
	/////////////////////////////////////////////////////////////////

	// Getter

	/**
	* Accessor method for grid of position objects
	* @return the grid of positions
	**/
	public Position[][] getGrid()
	{
		return grid;
	}

	/////////////////////////////////////////////////////////////////

	// Running the game

	/**
	* The mechanics of the gameplay. Calls methods to set ships and grenades, checks the status of each player, and prints results at the end of the game.
	* @see setShips
	* @see setGrenades
	* @see fillGrid
	*	@see getShips
	*	@see getGrenade
	* @see placeRocket
	* @see display
	* @see getName
	**/
	public void run()
	{
		// Welcome message
		System.out.println("\tWelcome to ||BATTLESHIP||\n\n");

		// Allows first human then computer players to set their pieces
		setShips(p1, scan);
		setGrenades(p1, scan);
		setShips(p2, scan);
		setGrenades(p2,scan);

		System.out.println("The Computer placed all his ships, let's play");

		// Fills the rest of the grid with default values
		fillGrid();

		// Evaluates whether the human or computer has run out of ships, and loops for each round of turns while true
		while (p1.getShips()!=0 && p2.getShips()!=0)
		{
			// Human's turn
			System.out.println("Your turn");
			placeRocket(p1, scan);
			display();
			// Checks if computer has any ships left after human's turn
			if (p2.getShips()==0){
				break;
			}

			// Computer's turn
			System.out.println("Computer's turn");
			placeRocket(p2, scan);
			display();
		}

		// Checks current status at the end of a round and prints results
		if (p1.getShips()==0)
			System.out.println("The winner is "+ p2.getName());
		else
			System.out.println("The winner is " + p1.getName());
		System.out.println("Number of missed turns for " + p1.getName() + " = " + p1.getMissedTurns());
		System.out.println("Number of missed turns for " + p2.getName() + " = " + p2.getMissedTurns());
		display();

	}
	/////////////////////////////////////////////////////////////////

	// Game methods
	/**
	* 'Places' ships at positions on the grid specified by the players. Computer uses a random coordinate.
	* @param p  the player passed from the run() method
	* @param sc	the scanner taking the player's input for a coordinate
	* @see Scanner
	* @see Player#getName
	* @see Position#Position(String, int, int)
	* @see Position#getRow
	*	@see Position#getColumn
	*	@see Position#setShip
	**/
	public void setShips(Player p, Scanner sc) // for the Human player and computer
	{
		// Check if it is the computer that has been called by the run method
		if (p.getName().equals("Computer"))
		{
			// Loop once for each ship to be placed
			for (int i=0; i<6; i++)
			{
				// Create new position object at a random coordinate
				Position x = new Position(p.getName(), (int)(Math.random()*8), (int)(Math.random()*8));

				// Check if the coordinate is available
				if (isAvailable(x.getRow(),x.getColumn()))
				{
					// Set the position as having a ship
					x.setShip();

					// Add the position object to the grid at that coordinate
					grid[x.getRow()][x.getColumn()]=x;
				}
				else
				{
					// If coordinate is not available, decrease counter so that another position is chosen
					i--;
					// Return to loop
					continue;
				}
			}
		}
		// The human has been called by the run method
		else
		{
			// Loop once for each ship to be placed
			for (int i=0; i<6; i++)
			{
				System.out.println("Enter the coordinate of your ship #" + (i+1) + ": ");

				// Get desired coordinate from human player
				String coordinate = sc.nextLine();

				// Check if desired coordinate is within grid
				if (isWithin(coordinate))
				{
					// Check is desired coordinate is available
					if (isAvailable(coordinate))
					{
						// Create position at the given coordinate
						Position x = new Position(p.getName(), coordinate);

						// Set the position object at the coordinate as having a ship
						x.setShip();

						// Place the position object at the specified coordinate in the grid
						grid[x.getRow()][x.getColumn()]=x;
					}
					else
					{
						System.out.println("Position not available, try another one.");
						// If coordinate is not available, decrease counter so that another position is chosen
						i--;
						// Return to loop
						continue;
					}
				}
				else
				{
					System.out.println("The coordinate you entered is invalid, try again");
					// If coordinate is not within the grid, decrease counter so that another position is chosen
					i--;
					// Return to loop
					continue;
				}

			}

		}

	}
	/**
	* 'Places' grenades at positions on the grid specified by the players. Computer uses a random coordinate.
	* @param p  the player passed from the run() method
	* @param sc	the scanner taking the player's input for a coordinate
	* @see Scanner
	* @see Player#getName
	* @see Position#Position(String, int, int)
	* @see Position#getRow
	*	@see Position#getColumn
	*	@see Position#setGrenade
	**/
	public void setGrenades(Player p, Scanner sc)
	{
		// Check if it is the computer that has been called by the run method
		if (p.getName().equals("Computer"))
		{
			// Loop once for each grenade to be placed
			for (int i=0; i<4; i++)
			{
				// Create new position object at a random coordinate
				Position x = new Position(p.getName(), (int)(Math.random()*8), (int)(Math.random()*8));

				// Check is desired coordinate is available
				if (isAvailable(x.getRow(),x.getColumn()))
				{
					// Set the position has having a grenade
					x.setGrenade();

					// Place the position object at the specified location in the grid
					grid[x.getRow()][x.getColumn()]=x;
				}
				else
				{
					// If coordinate is not available, decrease counter so that another position is chosen
					i--;
					// Return to loop
					continue;
				}
			}
		}
		// The human has been called by the run method
		else
		{
			// Loop once for each ship to be placed
			for (int i=0; i<4; i++)
			{
				System.out.println("Enter the coordinate of your grenade #" + (i+1) + ": ");
				String coordinate = sc.nextLine();
				// Check if desired coordinate is within grid
				if (isWithin(coordinate))
				{
					// Check is desired coordinate is available
					if (isAvailable(coordinate))
					{
						// Create position at the given coordinate
						Position x = new Position(p.getName(), coordinate);

						// Set the position has having a grenade
						x.setGrenade();

						// Place the position object at the specified location in the grid
						grid[x.getRow()][x.getColumn()]=x;
					}
					else
					{
						System.out.println("Position not available, try another one.");
						// If coordinate is not available, decrease counter so that another position is chosen
						i--;
						// Return to loop
						continue;
					}
				}
				else
				{
					System.out.println("The coordinate you entered is invalid, try again");
					// If coordinate is not within grid, decrease counter so that another position is chosen
					i--;
					// Return to loop
					continue;
				}
			}
		}
	}

	/**
	* Prints the grid using specific characters for computer's and human's pieces
	* @see Position
	* @see Position#isCalled
	* @see Position#getOwner
	* @see @Position#getShip
	* @see Position#getGrenade
	**/
	public void display()
	{
		// Loops through each row
		for (int i=0; i<8; i++)
		{
			System.out.println("");

			// In first row, print letter coordinate system
			if (i==0)
			{
				// Print a space so that row numbers can be placed underneath
				System.out.print(" ");

				// Loop through each column
				for (int k=0; k<8;k++)
				{
					// Print the letter corresponding to the column
					System.out.print((char)(k+65)+ " ");
				}
				System.out.println("");
			}

			// For successive rows, print the row number before the grid
			System.out.print(i+1);

			// Loop through each column
			for (int j=0; j<8; j++)
			{
				if (grid[i][j].isCalled()) // If the position is called
				{
					if (grid[i][j].getOwner().equals("Computer")) // If the player is the computer
					{
						if (grid[i][j].getShip()) // If there is a ship at that location
						{
							printGrid[i][j]='S'; // Print an uppercase S at the position on the grid

						}
						else // The computer owns the position, but does not have a ship there, so it must be a grenade
						{
							printGrid[i][j]='G'; // Print an uppercase G at the position on the grid
						}
					}
					else if (grid[i][j].getOwner().equals("Nobody")) // Position on the grid belongs to nobody
					{
						printGrid[i][j]='*'; // Print an asterisk
					}
					else // If it is human player
					{
						if (grid[i][j].getShip())
						{
							printGrid[i][j]='s';
						}
						else
						{
							printGrid[i][j]='g';
						}
					}

				}
				else // Position not called yet
				{
					printGrid[i][j]='_';
				}
				// Print a space between each column
				System.out.print(printGrid[i][j]+" ");
			}
		}
		System.out.println("");
	}

	/**
	* Discovers whether there is a piece at a specified position, and takes appropriate action if so.
	* Grenades make the calling player lose a turn. Ships trigger a decrease in the opponent's ship count.
	* @param p  the player passed from the run() method
	* @param sc	the scanner taking the player's input for a coordinate
	* @see Position
	* @see Position#isCalled
	* @see Position#getOwner
	* @see Position#getShip
	* @see Position#getGrenade
	* @see Position#isCalled
	* @see Position#setCalled
	* @see Position#isWithin
	* @see Player
	* @see Player#removeShips
	* @see Player#removeGrenade
	* @see Player#addMissedTurn
	* @see Player#getName
	* @see placeRocket
	* @see display
	**/
	public void placeRocket(Player p, Scanner sc)
	{
		if (p.getName().equals("Computer"))
		{
			int row,col;

			// Find a coordinate that isn't its own
			do
			{
				row=(int)(Math.random()*8);
				col=(int)(Math.random()*8);
			}
			while (grid[row][col].getOwner().equals("Computer"));

			// Prints out Computer's chosen coordinates
			System.out.println("The computer chose position: "+ (char)(col+65)+""+(char)(row+49));

			// If the chosen coordinate has a ship and has not yet been called, remove a ship from the human's ship count and set the position as called.
			if (grid[row][col].getShip())
			{
				if (!grid[row][col].isCalled())
				{
					p1.removeShips();
					grid[row][col].setCalled();
				}
			}
			/* If the chosen coordinate has a grenade and has not yet been called, remove a ship from the human's ship count and set position as called.
			* Make human player lose turn, display the grid and run a turn for the computer.
			*/
			else if(grid[row][col].getGrenade())
			{
				if (!grid[row][col].isCalled())
				{
					System.out.println("Computer hit a grenade!");
					p1.removeGrenade();
					grid[row][col].setCalled();
					p.addMissedTurn();
					display();
					placeRocket(p1,scan);
				}

			}
			// The position belongs neither to the computer nor the human. The position is set as called.
			else
			{
				grid[row][col].setCalled();
			}
		}
		// The human player places their rocket
		else
		{
			System.out.println("Where do you want to position your rocket?:");
			String coordinate = sc.nextLine();

			// Check if desired coordinate is within grid
			if (isWithin(coordinate))
			{
				// Temporary create object just to get row and column for the coordinate entered
				Position temp = new Position(p1.getName(), coordinate);
				if (grid[temp.getRow()][temp.getColumn()].getOwner().equals(p2.getName())) // The position belongs to the computer
				{
					if (grid[temp.getRow()][temp.getColumn()].getShip()) // There is a ship at the position
					{
						if (!grid[temp.getRow()][temp.getColumn()].isCalled())
						{
							System.out.println("Ship hit!");
							p2.removeShips();
							grid[temp.getRow()][temp.getColumn()].setCalled();
						}

					}
					else // There is a grenade at the position
					{
						if (!grid[temp.getRow()][temp.getColumn()].isCalled()) // If it has not been called, proceed with the process for grenades
						{
							System.out.println("You hit a grenade.");
							p2.removeGrenade();
							p.addMissedTurn();
							grid[temp.getRow()][temp.getColumn()].setCalled();
							display();
							placeRocket(p2,scan);
						}

					}

				}
				else if (grid[temp.getRow()][temp.getColumn()].getOwner().equals(p.getName())) // The position belongs to the argument player
				{
					System.out.println("This is your position!");
					// Repeat turn
					placeRocket(p1, scan);
				}
				else // Belongs to nobody
				{
					System.out.println("Nothing was hit");
					grid[temp.getRow()][temp.getColumn()].setCalled();
				}
			}
			else // Not a valid coordinate, repeat the turn
			{
				System.out.println("The coordinate you entered is invalid");
				placeRocket(p1,scan);
			}
		}
	}

	/**
	* Initializes the positions in the grid that have not been marked as the computer's or human's
	* @see Position
	**/
	public void fillGrid()
	{
		for (int i=0; i<8; i++)
		{
			for (int j=0; j<8;j++)
			{
				if (grid[i][j]==null)
				{
					grid[i][j] = new Position("Nobody", i, j);
				}
			}
		}
	}


}
