// Kurt Postlmayr
// COP 3503C, Fall 2021
// ku222809

import java.util.*;
import java.lang.*;

public class SneakyQueens
{
	public static boolean allTheQueensAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{
		// Only one queen
		if (coordinateStrings.size() == 1)
			return true;

		// If you have more queens than the length/width of the board then two must be in the same row/column
		if (coordinateStrings.size() > boardSize)
			return false;

		// All is fair so populate the maps
		HashSet <Integer> negativeDiagonal = new HashSet <Integer>(coordinateStrings.size()); 	// add (c + r)
		HashSet <Integer> positiveDiagonal = new HashSet <Integer>(coordinateStrings.size()); 	// sub (c - r)
		HashSet <Integer> columns = new HashSet <Integer>(coordinateStrings.size());			// store column
		HashSet <Integer> rows = new HashSet <Integer>(coordinateStrings.size());				// store row

		// Loops through queens list and calls method to get column/row then checks if queens valid
		for (int i = 0; i < coordinateStrings.size(); i++)
		{
			int[] columnAndRow = getColumnAndRow(coordinateStrings.get(i));
			int column = columnAndRow[0];
			int row = columnAndRow[1];

			int add = (column + row);
			int sub = (column - row);

			// Set all possibilities to true and if it is already true then there is a queen there so return false
			if (negativeDiagonal.contains(add) || positiveDiagonal.contains(sub) || columns.contains(column) || rows.contains(row))
			{
				return false;
			}
			else
			{
				negativeDiagonal.add(add);
				positiveDiagonal.add(sub);
				columns.add(column);
				rows.add(row);
			}
		}

		return true;
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
