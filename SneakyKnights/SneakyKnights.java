// Kurt Postlmayr
// COP 3503C, Fall 2021
// ku222809

import java.io.*;
import java.util.*;

public class SneakyKnights
{
	public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{
		// Only one knight
		if (coordinateStrings.size() == 1)
			return true;

		// Hashmap with inner hashset as value to verify column (outer) and row (inner)
		HashMap<Integer, HashSet<Integer>> knightMap = new HashMap<Integer, HashSet<Integer>>();

		// Looping through coordinateStrings. Add and check if they can attack
		for (String s : coordinateStrings)
		{
			// Reuse of Horner's rule algorithm to get column (0) and row (1)
			int[] columnAndRow = getColumnAndRow(s);
			int column = columnAndRow[0];
			int row = columnAndRow[1];

			// Put if absent incase another knight has been to that column, a new HashSet is created
			knightMap.putIfAbsent(column, new HashSet<Integer>());
			knightMap.get(column).add(row);

			// Loop for adjusting attack positions. i = 1 then column - 1, row - 2
			for (int i = 1; i <= 2; i++)
			{
				// Get difference for row using remainder of i. (1 % 2 = 1 | 1 + 1 = 2) (2 % 2 = 0 | 0 + 1 = 1)
				int rowDiff = (i % 2) + 1;

				// Not saving attack positions since if one knight can attack then the other can also attack
				// So simply check if theres a knight saved in the attack positions
				if (column + i <= Integer.MAX_VALUE)
				{
					knightMap.putIfAbsent(column + i, new HashSet<Integer>());

					// Conditionals prevent passing a value larger than int max into map
					if (row + rowDiff <= Integer.MAX_VALUE)
						if (knightMap.get(column + i).contains(row + rowDiff))
							return false;
					if (row - rowDiff > 0)
						if (knightMap.get(column + i).contains(row - rowDiff))
							return false;
				}

				// Conditional not really necessary since no knight will be "off grid". Looks nice though
				if (column - i > 0)
				{
					knightMap.putIfAbsent(column - i, new HashSet<Integer>());

					if (row + rowDiff <= Integer.MAX_VALUE)
						if (knightMap.get(column - i).contains(row + rowDiff))
							return false;
					if (row - rowDiff > 0)
						if (knightMap.get(column - i).contains(row - rowDiff))
							return false;
				}
			}
		}

		// Loop is broken, all knights checked so return valid
		return true;
	}

	// Using Horner's Rule this function disects the string argument and creates an int array
	// Storing index 0 as column and index 1 as row.
	public static int[] getColumnAndRow(String coordinate)
	{
		int[] columnAndRow = new int[2];
		int column = 0;
		int row = 0;

		for (int i = 0; i < coordinate.length(); i++)
		{
			char addChar = coordinate.charAt(i);

			// It's a letter (based on pdf)
			if ((int)addChar > 57) // Magic number
			{
				column *= 26; // Magic number
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
		return 3.0;
	}

	public static double hoursSpent()
	{
		return 7.0;
	}
}
