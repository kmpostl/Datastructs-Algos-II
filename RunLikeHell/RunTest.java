import java.util.*;
import java.lang.*;

public class RunTest
{
	static int count = 0;
	public static void main(String[] args)
	{
		//int [] arr = { 15, 3, 6, 17, 2, 1, 20, 50, 13, 45, 9, 11, 68, 31 };
		//int [] arr = { 15, 3, 6, 17, 2, 1, 20 };
		int [] blocks = {0,1,2,3,4,5,6,7,8,9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		//int [][] multiples = new int[arr.length][arr.length];

		//multiples(multiples, arr, 2, 0);
		//iterativeSol(arr, multiples);
		//System.out.println(realBasic(arr, 2));
		//System.out.println("Count of iterations: " + count);
		//print2DArray(multiples);

		//int [] blocks = new int[] {1, 15, 3, 6, 17, 2, 1, 20};
		//int [] blocks = new int[] {9, 20, 13, 16, 9, 9, 6};
		//int [] blocks = new int[] {15, 3, 6, 17, 2, 1, 20};
		System.out.println("Recursive Max is: " + realBasic(blocks, 0));
		System.out.println("DP Max is: " + realBasicDp(blocks));
	}
	public static void iterativeSol(int[] blocks, int[][] multiples)
	{
		int max = 0;
		for (int i = 2; i < blocks.length; i++)
		{
			int sum = 0;
			int j = 0;
			for (j = 0; j < blocks.length; j+=i)
			{
				multiples[i][j] = blocks[j];
				sum += blocks[j];
			}
			//blocks[j + 1] = sum;
			max = Math.max(max, sum);
		}
	}
	public static int multiples(int[][] multiples, int[] blocks, int jump, int track)
	{
		if (track >= blocks.length || jump >= blocks.length)
			return 0;
		if (track + jump >= blocks.length)
			return blocks[track];
		multiples[jump][track] = multiples(multiples, blocks, jump, track+jump);
		if (jump + 1 < blocks.length)
			multiples[jump+1][track] = multiples(multiples, blocks, jump+1, track);

		return 0;
	}

	public static void print2DArray(int[][] arr)
	{
		for(int i = 0; i < arr.length; i++)
		{
			System.out.printf("Jump "+ i + ":%10s" , " ");
			for (int j = 0; j < arr[i].length; j++)
			{
				System.out.printf(arr[i][j] + "%10s" , " ");
			}
			System.out.println();
		}
	}

	public static int realBasic(int[] blocks, int n)
	{
		if (n >= blocks.length)
			return 0;

		return Math.max(blocks[n] + realBasic(blocks, n + 2), realBasic(blocks, n + 1));
	}

	public static int realBasicDp(int[] blocks)
	{
		int firstElementTwo = blocks[0];
		int firstElementThree = blocks[0];
		int secondElementTwo = 0;
		int secondElementThree = 0;

		for (int i = 1; i < blocks.length; i++)
		{
			if (i % 2 == 0)
				firstElementTwo += blocks[i];
			if (i % 3 == 0)
				firstElementThree += blocks[i];

			int ii = i-1;

			if (ii % 2 == 0)
				secondElementTwo += blocks[i];
			if (ii % 3 == 0)
				secondElementThree += blocks[i];

		}

		int max = Math.max(firstElementTwo, firstElementThree);
		max = Math.max(max, secondElementTwo);
		max = Math.max(max, secondElementThree);

		return max;
	}

	// public static int dpSol(int[] blocks)
	// {
	// 	int n = blocks.length;
	//
	// 	for (int i = 2; i < n; i++)
	// 	{
	// 		int high = blocks[n-1];
	// 		int low = blocks[i];
	//
	// 		if ((n-1)%i == 0 && (n-1) != i)
	//
	// 		n-=1
	// 	}
	//
	// 	return ????arr[index]??? + blocks[0];
	// }
}
