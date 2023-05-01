// Kurt Postlmayr
// COP 3503C, Fall 2021
// ku222809

import java.util.*;
import java.lang.*;

public class SneakyQueens2
{

	static HashMap <Integer, Boolean> negativeDiagonal = new HashMap <Integer, Boolean>(); 	// add (c + r)
	static HashMap <Integer, Boolean> positiveDiagonal = new HashMap <Integer, Boolean>(); 	// sub (c - r)
	static HashMap <Integer, Boolean> columns = new HashMap <Integer, Boolean>();			// store column
	static HashMap <Integer, Boolean> rows = new HashMap <Integer, Boolean>();				// store row

	public static void main(String[] args)
	{
		ArrayList <String> tester = new ArrayList <String>();
		System.out.println(allTheQueensAreSafe(tester, 4));
	}

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

		for (int i = 0; i < coordinateStrings.size(); i++) 
		{
			int column = getColumn(coordinateStrings.get(i));
			int row = Integer.parseInt(coordinateStrings.get(i).replaceAll("[a-z]",""));

			int add = (column + row);
			int sub = (column - row);
			
			// Set all possibilities to true and if it is already true then there is a queen there so return false
			if(negativeDiagonal.get(add) == true || positiveDiagonal.get(sub) == true || columns.get(column) == true || rows.get(row) == true)
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

	public static int getColumn(String coordinate)
	{
		String column = coordinate.replaceAll("[0-9]","");
		int value = 0;
		int count = column.length() - 1;

		for (int i = 0; i < column.length(); i++)
		{
			value += (Character.getNumericValue(column.charAt(i)) - 9) * (Math.pow(26, count));
			count--;
		}

		return value;
	}

	public static double difficultyRating()
	{
		return 2.8;
	}

	public static double hoursSpent()
	{
		return 9.0;
	}

}
