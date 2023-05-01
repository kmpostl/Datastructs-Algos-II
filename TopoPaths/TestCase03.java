// Sean Szumlanski
// COP 3503, Fall 2021

// ==========================
// TopoPaths: TestCase03.java
// ==========================
// This corresponds to graph G1 from the assignment PDF.


import java.io.*;

public class TestCase03
{
	public static void main(String [] args) throws IOException
	{
		System.out.print("Test Case #3: ");

		boolean success = (TopoPaths.countTopoPaths("input_files/TestCase03-graph.txt") == 0);
		System.out.println(success ? "PASS" : "fail whale :(");
	}
}
