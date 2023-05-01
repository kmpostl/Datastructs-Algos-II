// Kurt Postlmayr
// COP 3503C, Fall 2021
// ku222809

import java.util.*;
import java.lang.*;

public class SneakyQueens
{
	static HashMap <Integer, Boolean> negativeDiagonal = new HashMap <Integer, Boolean>(); 	// add (c + r)
	static HashMap <Integer, Boolean> positiveDiagonal = new HashMap <Integer, Boolean>(); 	// sub (c - r)
	static HashMap <Integer, Boolean> columns = new HashMap <Integer, Boolean>();			// store column
	static HashMap <Integer, Boolean> rows = new HashMap <Integer, Boolean>();				// store row

	public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{
		// Only one queen
		if (coordinateStrings.size() == 1)
			return true;

		// If you have more queens than the length/width of the board then two must be in the same row/column
		if (coordinateStrings.size() > boardSize)
			return false;

		// All is fair so populate the maps
		populateHashmaps(boardSize);

		// Loops through queens list and calls method to get column/row then checks if queens valid
		for (int i = 0; i < coordinateStrings.size(); i++) 
		{
			int[] columnAndRow = getColumnAndRow(coordinateStrings.get(i));
			int column = columnAndRow[0];
			int row = columnAndRow[1];

			int add = (column + row);
			int sub = (column - row);
			
			// Set all possibilities to true and if it is already true then there is a queen there so return false
			if (negativeDiagonal.get(add) || positiveDiagonal.get(sub) || columns.get(column) || rows.get(row))
			{
				return false;
			}
			else
			{
				negativeDiagonal.replace(add, true);
				positiveDiagonal.replace(sub, true);
				columns.replace(column, true);
				rows.replace(row, true);
			}
		}
		
		return true;
	}

	// Populates verification maps based on matrix algebra of chess board
	public static void populateHashmaps(int boardSize)
	{
		int count = boardSize;

		for (int i = 0; i < boardSize; i++) 
		{
			negativeDiagonal.put(i + 2, false);
			negativeDiagonal.put(((boardSize * 2) - i), false);

			positiveDiagonal.put((i - (boardSize - 1)), false);
			positiveDiagonal.put(i, false);

			columns.put((i + 1), false);
			rows.put((i + 1), false);
		}
	}

	// Using Horner's Rule this function disects the string argument and creates an int array 
	// Storing index 0 as column and index 1 as row. 
	// Couldn't think of another way other than an object or globals again; since no pointers :(
	public static int[] getColumnAndRow(String coordinate)
	{
		int[] columnAndRow = new int[2];
		int column = 0;
		int row = 0;

		for (int i = 0; i < coordinate.length(); i++)
		{
			char addChar = coordinate.charAt(i);

			// It's a letter (based on pdf)
			if ((int)addChar > 57)
			{
				column *= 26;
				column += ((int)((addChar - 'a') + 1));
			}
			// It's a number (based on pdf)
			else
			{
				row *= 10;
				row += ((int)(addChar - '0'));
			}
		}

		columnAndRow[0] = column;
		columnAndRow[1] = row;

		return columnAndRow;
	}

	public static double difficultyRating()
	{
		return 2.9;
	}

	public static double hoursSpent()
	{
		return 9.5;
	}
}
