/*
 * Samuel Gosselin and John Stark
 * COMP 249
 * Assignment #1
 * February 1, 2017
*/

/**
* The player class.
* @author Samuel Gosselin
* @author John Stark
* @version 2.00
**/
public class Player {

	private int missedTurns;
	private int ships;
	private int grenades;
	private String name;

		/**
		* Increases the player's count of missed turns. When this number is greater than the total number of terms,
		* the Battleship class will repeat a player's turn.
		**/
		public void addMissedTurn()
		{
			missedTurns++;
		}

		/**
		* Returns the missed turn counter.
		* @return	the missed turn count of the player
		**/
		public int getMissedTurns()
		{
			return missedTurns;
		}

		/**
		* Default constructor for the player. Sets number of ships and grenades to defaults, and sets the player has the computer.
		**/
		public Player()
		{
			ships=6;
			grenades=4;
			name="Computer";

		/**
		* Constructor for the human player. Sets number of ships and grenades to defaults, and sets the player's name.
		**/
		}
		public Player(String name)
		{
			ships=6;
			grenades=4;
			this.name=name;

		}

		/**
		* Accessor for the player's name
		* @return	name of player
		**/
		public String getName()
		{
			return name;
		}
		public void removeShips()
		{
			ships--;
		}
		public void removeGrenade()
		{
			grenades--;
		}

		/**
		* Accessor for the player's ship count
		* @return	number of ships the player has left
		**/
		public int getShips()
		{
			return ships;
		}

		/**
		* Accessor for the player's grenade count
		* @return	number of grenade the player has left
		**/
		public int getGrenades()
		{
			return grenades;
		}

	}
