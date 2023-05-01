// Kurt Postlmayr
// COP 3503C, Fall 2021
// ku222809

import java.util.*;
import java.io.*;

public class TopoPaths
{
	// Public wrapper for countTopoPaths
	public static int countTopoPaths(String filename) throws IOException
	{
		// Declare needed variables
		Scanner scan = new Scanner(new File(filename));
		int nodeCount = scan.nextInt();
		// Declare array list with size so we dont have to recreate it with O(n) time for each insert
		ArrayList<LinkedList<Integer>> list = new ArrayList<LinkedList<Integer>>(nodeCount);
		// Keeps track of the indegree of each node as we remove nodes from the queue
		int[] inDegree = new int[nodeCount];
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Private method call to populate the list
		populateList(nodeCount, scan, list, inDegree);

		// Find the 0 in degree node and put it in the queue.
		// This could probably get done within populateList method. This just seemed safer
		for (int i = 0; i < inDegree.length; i++)
			if (inDegree[i] == 0)
				queue.add(i);

		// More than 2 nodes have no directed edges to themselves, therefore path is not possible
		if (queue.size() > 1)
			return 0;

		// Actual looping method called
		return countTopoPaths(list, queue, inDegree);
	}

	// Dr. Szumlanski's code was referenced to build this method
	private static int countTopoPaths(ArrayList<LinkedList<Integer>> list, LinkedList<Integer> queue, int[] inDegree)
	{
		// uniqueCount keeps track for cycles, while count gives us the true count if no cycle
		int node;
		int count = 1;
		int howManyNewZeros;
		int uniqueCount = 0;

		// Queue only ever contains nodes with indegree = 0, and then checks their adjacent nodes
		while (!queue.isEmpty())
		{
			uniqueCount++;
			node = queue.remove();
			howManyNewZeros = 0;

			// Loop's trough adjacent nodes to current within the list
			for (int i = 0; i < list.get(node).size(); i++)
			{
				int adjacentNode = list.get(node).get(i);

				// When a node's indegree becomes 0, due to removal of current, it is added to the queue
				if (--inDegree[adjacentNode] == 0)
				{
					queue.add(adjacentNode);
					// Used to determine what we need to multiply the count by to get result
					howManyNewZeros++;
				}
			}

			// Increase count by product of number of nodes with zero incoming since any can be taken
			if (howManyNewZeros > 0)
				count *= howManyNewZeros;
		}

		// There's a cycle
		if (uniqueCount != list.size())
			return 0;

		return count;
	}

	// Fills the adjacency list with all adjacent nodes for all nodes
	private static void populateList(int nodeCount, Scanner scan,
										ArrayList<LinkedList<Integer>> list, int[] inDegree)
	{
		int node = 0;
		int howManyAdjacent;

		while (node < nodeCount)
		{
			// First time list's index is touched so it needs a new linked list
			list.add(new LinkedList<Integer>());
			howManyAdjacent = scan.nextInt();
			int nextNode;

			// Grab all adjacent nodes based on file input format
			for (int i = 0; i < howManyAdjacent; i++)
			{
				nextNode = scan.nextInt();
				// For consistency subtract 1 so that nodes number corresponds with it's list index
				list.get(node).add(nextNode - 1);
				// Increment the in degree of the node that the directed edge is towards
				inDegree[nextNode - 1]++;
			}

			node++;
		}
	}

	public static double difficultyRating()
	{
		return 2.0;
	}

	public static double hoursSpent()
	{
		return 6.0;
	}
}
