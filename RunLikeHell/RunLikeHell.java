// Kurt Postlmayr
// COP 3503C, Fall 2021
// ku222809

import java.util.*;
import java.lang.*;

public class RunLikeHell
{
	// Recursive Wrapper
	public static int maxGainRecursive(int [] blocks)
	{
		return maxGainRecursive(blocks, 0);
	}

	// Recursive Method
	public static int maxGainRecursive(int [] blocks, int n)
	{
		if (n >= blocks.length)
			return 0;

		return Math.max(blocks[n] + maxGainRecursive(blocks, n + 2), maxGainRecursive(blocks, n + 1));
	}

	// Memoized wrapper
	public static int maxGainMemoized(int [] blocks)
	{
		int [] memo = new int [blocks.length];

		return maxGainMemoized(blocks, memo, 0);
	}

	// Memoized Method
	public static int maxGainMemoized(int [] blocks, int [] memo, int n)
	{
		if (n >= blocks.length)
			return 0;
		// Gimme thahhat Memo
		if (memo[n] != 0)
		{
			System.out.println("**DING!**");
			return memo[n];
		}
		// Memo thahhat
		memo[n] = Math.max(blocks[n] + maxGainMemoized(blocks, memo, n + 2), maxGainMemoized(blocks, memo, n + 1));
		return memo[n];
	}

	// Dynamic Programming solution
	// This can be redone with 3 ints instead but it seems too risky to me and my brain is fried
	// Fills right to left and compares the current block + the 2nd to last max with the previous max
	// Since we cant grab two blocks right next to each other
	public static int maxGain(int[] blocks)
	{
		// Already instantiated to 0's
		int [] matrix = new int [blocks.length + 2];

		for (int i = blocks.length - 1; i >= 0; i--)
			matrix[i] = Math.max(blocks[i] + matrix[i + 2], matrix[i + 1]);

		return matrix[0];
	}

	// Defintely went way over but 99% of the time was from a misunderstanding
	// I thought we had to find the best jump sequence and had to grab the first block.
	// I went down a huge rabbit hole and came to the conclusion that it is not possible in O(n)
	public static double hoursSpent()
	{
		return 10.0;
	}

	// The other problem I would have rated a 10
	public static double difficultyRating()
	{
		return 1.9;
	}
}
