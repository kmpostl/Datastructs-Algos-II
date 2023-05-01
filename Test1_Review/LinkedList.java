import java.util.*;
import java.lang.*;

class Node<Type>
{
	Type data;
	Node<Type> next;

	Node(Type data)
	{
		this.data = data;
		this.next = null;
	}
}

public class LinkedList<Type extends Comparable<Type>>
{
	public Node<Type> head,tail;

	public LinkedList()
	{
		this.head = null;
		this.tail = head;
	}

	public void insertNode(Type newType)
	{
		Node<Type> newNode = new Node<Type>(newType);
		if (this.head == null)
		{
			this.head = newNode;
			this.tail = head;
			this.head.next = null;
		}
		else
		{
			this.tail.next = newNode; // newNode next = null
			this.tail = this.tail.next;
		}
	}

	public Type deleteIterative(Type obj)
	{
		Node<Type> current = head;
		Node<Type> previous = head;		// Doesnt matter what i define as rn


		while (current != null && current.data.compareTo(obj) != 0)
		{
			previous = current;
			current = current.next;
		}

		if (current == null) // If head is null it gets returned here
			return null;

		previous.next = current.next;

		return current.data;
	}

	public Type deleteRecursive(Type obj) // null is kinda an issue, requires user to be safe
	{
		Node<Type> current = this.head;
		if (current == null)
			return null;
		return deleteRecursiveWrapped(current, obj).data;
	}

	private Node<Type> deleteRecursiveWrapped(Node<Type> current, Type obj)	// could also just return the Type data of node when found. null is kinda an issue, requires user to be safe
	{
		if (current.next.data.compareTo(obj) == 0)
		{
			Node<Type> deleted = current.next;
			current.next = deleted.next;
			deleted.next = null;
			return deleted;
		}
		else if (current.next == null)
			return null;

		return deleteRecursiveWrapped(current.next, obj);
	}

	public String toString() // Wraper
	{
		if(head == null)
			return null;

		Node<Type> addMe = head;
		return stringMe(addMe);
	}

	private String stringMe(Node<Type> addMe) // Recursive String
	{
		return (addMe.next == null ? addMe.data.toString() : addMe.data.toString() + " : " + stringMe(addMe.next));
	}
}
