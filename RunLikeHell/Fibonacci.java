import java.util.*;
import java.lang.*;

public class Fibonacci
{
	public static void main(String[] args)
	{
		System.out.println("Memoized recursive result is: " + fibMemo(45));
		System.out.println("Dynamic Programming result is: " + fibDP(1000));
		System.out.println("Regular recursive result is: " + fibRec(45));
	}

	// Recursive
	public static int fibRec(int n)
	{
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return fibRec(n-1) + fibRec(n-2);
	}

	// Recursive memoized
	public static int fibMemo(int n)
	{
		int[] memo = new int[n + 1];
		Arrays.fill(memo, -1);
		memo[0] = 0;
		memo[1] = 1;
		fibMemo(n, memo);

		return memo[n];
	}
	private static int fibMemo(int n, int[] memo)
	{
		if (memo[n] != -1)
		{
			return memo[n];
		}
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		memo[n] = fibMemo(n-1, memo) + fibMemo(n-2, memo);
		return memo[n];
	}

	// Dynamic Programming
	public static int fibDP(int n)
	{
		int[] dpArr = new int[n + 1];

		dpArr[0] = 0;
		dpArr[1] = 1;

		for (int i = 2; i < dpArr.length; i++)
		{
			dpArr[i] = dpArr[i-1] + dpArr[i-2];
		}

		return dpArr[n];
	}
}
