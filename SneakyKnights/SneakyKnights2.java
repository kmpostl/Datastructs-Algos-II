// Kurt Postlmayr
// COP 3503C, Fall 2021
// ku222809

import java.io.*;
import java.util.*;
import java.lang.*;

// 8 attack positions
	// (col + 1), (row - 2) - bottommost right
	// (col + 1), (row + 2) - topmost right
	// (col + 2), (row + 1) - top right
	// (col + 2), (row - 1) - bottom right
	// (col - 1), (row - 2) - bottommost left
	// (col - 1), (row + 2) - topmost left
	// (col - 2), (row + 1) - top left
	// (col - 2), (row - 1) - bottom left
	// if subtracting check if its less than 1 then its not a valid attack
	// if adding check if greater than boardSize then its not a valid attack

// Single hashset storing strings. no need to take int value of row and column

public class SneakyKnights
{

	public static boolean allTheKnightsAreSafe(ArrayList<String> coordinateStrings, int boardSize)
	{

		/*long newBoardSize = boardSize / 2 * boardSize;

		if (boardSize % 2 == 0)	// Even
			if (coordinateStrings.size() > newBoardSize)
				return false;
		else // Odd
			if (coordinateStrings.size() > (newBoardSize + 1))
				return false;
		*/


		/*				if (column + i < Integer.MAX_VALUE)
							knightMap.putIfAbsent(column + i, new HashSet<Integer>());
						if (column - i > 0)
							knightMap.putIfAbsent(column - i, new HashSet<Integer>());

						// No need to save attack positions since if one knight can attack then the other can also attack
						// So simply check if theres a knight saved in the attack positions
						if (column + i < Integer.MAX_VALUE && row + rowDiff < Integer.MAX_VALUE)
							if (knightMap.get(column + i).contains(row + rowDiff))
								return false;
						if (column - i > 0 && row + rowDiff < Integer.MAX_VALUE)
							if (knightMap.get(column - i).contains(row + rowDiff))
								return false;
						if (column + i < Integer.MAX_VALUE && row - rowDiff > 0)
							if (knightMap.get(column + i).contains(row - rowDiff))
								return false;
						if (column - i > 0 && row - rowDiff > 0)
							if (knightMap.get(column - i).contains(row - rowDiff))
								return false;
			*/

		//int maxRoot =
		// Only one knight
		if (coordinateStrings.size() == 1)
			return true;
		// No way for any knights to attack (may be dangerous with int max)
		/*if (boardSize % 2 == 0)	// Even
			if (coordinateStrings.size() > ((1/2) * (boardSize * boardSize)))
				return false;
		else // Odd
			if (coordinateStrings.size() > ((1/2) * ((boardSize * boardSize) + 1)))
				return false;*/

		// Redo with hasset as inner hash table because no knight will be duplicated position.
		HashMap<Integer, HashMap<Integer, Byte>> test = new HashMap<Integer, HashMap<Integer, Byte>>();
		//HashMap<Integer, HashSet<Integer>> test = new HashMap<Integer, HashSet<Integer>>();

		// You do need a hashmap of integers and booleans in the inner hashmap, since two knights can attack the same position without necessarily attacking each other
		// NO^ just dont save attacked cells. They can attack each other so simply checking will verify
		//BitSet columns = new BitSet(boardSize);
		//BitSet rows = new BitSet(boardSize);

		// !!!!! Still Need to stop checks off board. May not matter since knight positions will always be valid?
		//1,1 would be valid but -1,0 (attack position) is not
		for (String s : coordinateStrings)
		{
			int[] columnAndRow = getColumnAndRow(s);
			int column = columnAndRow[0];
			int row = columnAndRow[1];
			//System.out.println(s);
			// Here for readablity. Set up values for 8 attacking positions
			/*int colPlusOne = column + 1;
			int colPlusTwo = column + 2;
			int colMinusTwo = column - 2;
			int colMinusOne = column - 1;
			int rowPlusOne = row + 1;
			int rowPlusTwo = row + 2;
			int rowMinusTwo = row - 2;
			int rowMinusOne = row - 1;

			arr[0] = colPlusOne;
			arr[1] = colPlusTwo;
			arr[2] = colMinusTwo;
			arr[3] = colMinusOne;
			arr[4] = rowPlusOne;
			arr[5] = rowPlusTwo;
			arr[6] = rowMinusTwo;
			arr[7] = rowMinusOne;*/

			// 1 means attacked, 2 means knight is there

			for (int i = 1; i <= 2; i++)
			{
				// Get difference for row using remainder of i. (1 % 2 = 1 | 1 + 1 = 2) (2 % 2 = 0 | 0 + 1 = 1)
				int rowDiff = (i % 2) + 1;

				// No need to save attack positions since if one knight can attack then the other can also attack
				// So simply check if theres a knight saved in the attack positions
				knightMap.putIfAbsent(column + i, new HashSet<Integer>());
				knightMap.putIfAbsent(column - i, new HashSet<Integer>());

				if (knightMap.get(column + i).contains(row + rowDiff))
					return false;

				if (knightMap.get(column + i).contains(row - rowDiff))
					return false;

				if (knightMap.get(column - i).contains(row + rowDiff))
					return false;

				if (knightMap.get(column - i).contains(row - rowDiff))
					return false;
			}



			for (int i = 1; i <= 2; i++)
			{
				// Get difference for row using remainder of i. (1 % 2 = 1 | 1 + 1 = 2) (2 % 2 = 0 | 0 + 1 = 1)
				int rowDiff = (i % 2) + 1;

				// No need to save attack positions since if one knight can attack then the other can also attack
				// So simply check if theres a knight saved in the attack positions
				if (column + i < Integer.MAX_VALUE)
				{
					knightMap.putIfAbsent(column + i, new HashSet<Integer>());

					if (row + rowDiff < Integer.MAX_VALUE)
						if (knightMap.get(column + i).contains(row + rowDiff))
							return false;
					if (knightMap.get(column + i).contains(row - rowDiff))
						return false;
				}

				// Conditional not really necessary since no knight will be "off grid". Looks nice though
				knightMap.putIfAbsent(column - i, new HashSet<Integer>());

				if (row + rowDiff < Integer.MAX_VALUE)
					if (knightMap.get(column - i).contains(row + rowDiff))
						return false;
				if (knightMap.get(column - i).contains(row - rowDiff))
					return false;
			}


			byte one = 1;
			byte two = 2;
			test.putIfAbsent(column, new HashMap<Integer, Byte>());

			if (test.get(column).get(row) != null && test.get(column).get(row) == one)
				return false;
			else
			{
				test.get(column).put(row, two);

				for (int i = 1; i <= 2; i++)
				{
					int rowDiff = (i % 2) + 1;
					test.putIfAbsent(column + i, new HashMap<Integer, Byte>());
					test.putIfAbsent(column - i, new HashMap<Integer, Byte>());

					if (test.get(column + i).get(row + rowDiff) != null && test.get(column + i).get(row + rowDiff) == two)
					{
						//System.out.println(column + i + " " + (row + rowDiff));
						//System.out.println(i + " " + rowDiff + " " + column + " " + row);
						return false;
					}
					else
					{
						test.get(column + i).put(row + rowDiff, one);
					}
					if (test.get(column - i).get(row + rowDiff) != null && test.get(column - i).get(row + rowDiff) == two)
					{
						return false;
					}
					else
					{
						test.get(column - i).put(row + rowDiff, one);
					}
					if (test.get(column + i).get(row - rowDiff) != null && test.get(column + i).get(row - rowDiff) == two)
					{
						return false;
					}
					else
					{
						test.get(column + i).put(row - rowDiff, one);
					}
					if (test.get(column - i).get(row - rowDiff) != null && test.get(column - i).get(row - rowDiff) == two)
					{
						return false;
					}
					else
					{
						test.get(column - i).put(row - rowDiff, one);
					}
				}
			}
		}
		return true;
	}

	// Shouldn't be necessary
	/*public static boolean isValid(int[] columnAndRow, int boardSize)
	{
		if (columnAndRow[0] > boardSize || columnAndRow[1] > boardSize)
			return false;
		if(columnAndRow[0] < 1 || columnAndRow[1] < 1)
			return false;
		return true;
	}*/

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

	////////////////////////////////////////////////////////////////////////////////////DELETE/////////////////////////////////////////////////////////////////////////////////
	/*public static void main(String [] args) throws Exception
	{
		Scanner in = new Scanner(new File("input_files/TestCase03-input.txt"));
		ArrayList<String> list = new ArrayList<String>();

		// Read each line from the input file into the ArrayList.
		while (in.hasNext())
			list.add(in.next());

		System.out.println(allTheKnightsAreSafe(list, 4));
	}*/

	public static double difficultyRating()
	{
		return 3.0;
	}

	public static double hoursSpent()
	{
		return 7.0;
	}
}
