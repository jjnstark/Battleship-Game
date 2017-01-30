/*
 * Samuel Gosselin and John Stark
 * COMP 249
 * Assignment #1
 * February 1, 2017
*/

/**
* Class for the 'position' object. A position includes a set of coordinates, an owner, boolean values for whether there is a ship or grenade at the position, and whether the position has been called yet.
* A double arracol of position objects forms the grid for gameplay.
* @author Samuel Gosselin
* @author John Stark
* @version 2.00
**/
public class Position {

	private boolean ship;
	private boolean grenade;
	private boolean called;
	private int row;
	private int col;
	private String owner;
	
	/**
	* Constructor for position object. Sets the object's coordinates as the specified alphanumeric location.
	* @param owner	the owner of the position.
	* @param coordinate	the alphanumeric coordinate, e.g. A1
	**/
	public Position(String owner, String coordinate)
	{
		// The position has not yet been called
		called=false;
		// Set owner as that in parameter
		this.owner=owner;

		// Switch statement to match beginning of alphanumeric coordinate to column number
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
		// Switch statement to match last character of alphanumeric coordinate to row number
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
	}

	/**
	* Constructor for position object using coordinates in numeric form.
	* @param owner	The owner of the position
	* @param row	The row number
	* @param col	The column number
	**/
	public Position(String owner, int row , int col) // constructor
	{
		// Set instance variables to those specified in parameters
		this.owner=owner;
		this.row=row;
		this.col=col;
		called=false;
	}

	/**
	* Accessor method for the row number
	* @return	the row number of the position
	**/
	public int getRow()
	{
		return row;
	}

	/**
	* Accessor method for the column number
	* @return the column number of the position
	**/
	public int getColumn()
	{
		return col;
	}

	/**
	* Mutator method to set the position as having a ship.
	**/
	public void setShip()
	{
		ship=true;
	}

	/**
	* Accessor method for ship variable
	* @return whether there is a ship at the position
	**/
	public boolean getShip()
	{
		return ship;
	}

	/**
	* Mutator method for called variable. Sets the position has having been called.
	**/
	public void setCalled()
	{
		called=true;
	}

	/**
	* Accessor method for called variable.
	* @return	whether the position has been called.
	**/
	public boolean isCalled()
	{
		return called;
	}

	/**
	* Mutator method to set the position as having a grenade.
	**/
	public void setGrenade()
	{
		grenade=true;
	}

	/**
	* Accessor method for ship variable
	* @return whether there is a grenade at the position
	**/
	public boolean getGrenade()
	{
		return grenade;
	}

	/**
	* Accessor method for owner
	* @return the owner of the position.
	**/
	public String getOwner()
	{
		return owner;
	}
}
