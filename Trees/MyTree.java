package Trees;
import java.util.*;

/*
Implements a binary search tree
*/

public class MyTree 
{
	private Node root;

	class Node {
		private int data;
		private Node left,right;

		public Node (int data) {
			this.data = data;		
		}
	}


	/*
	1. If root is null then initialize the root with a new node and return. Else skip.
	2. Create a temporary pointer pointing at root initially. 
	3. Navigate the pointer to current node's left child if the given value is less than current node's value. Else navigate to right child.
	4. At anytime navigating to left or right child, if the pointer becomes null, create a new node here and return.
	*/

	public void insertNode (int data) {

		// check if root exists
		if (root == null) {
			root = new Node(data);
			return;
		}

		Node temp = root;
		
		while (true) {
			if (data <= temp.data) {
				if (temp.left != null) {
					temp = temp.left;
				}
				else {
					temp.left = new Node(data);
					break;
				}
			}
			else {
				if (temp.right != null) {
					temp = temp.right;
				}
				else {
					temp.right = new Node(data);
					break;
				}
			}
		}

	}


	private List<Integer> getRootToNodePath(int n1) throws Exception {
		List<Integer> path = new ArrayList<Integer>();
		
		if (root == null) {
			throw new Exception();
		}

		// find n1
		Node temp = root;

		while (true) {
			path.add(temp.data);
			if (n1 == temp.data) {
				break;
			}

			if (n1 < temp.data) {
				temp = temp.left;
			}
			else {
				temp = temp.right;
			}

			if (temp == null) {
				throw new Exception();
			}		
		}

		return path;
			
	}


	/*
	1. If root is null throw exception
	2. Assign minimum of (n1,n2) to x and the other value to y
	3. Using the property of BST, find the value starting from root that falls in the range [x,y]. This value is the least common ancestor.

	*/

	public int lowestCommonAncestor (int n1, int n2) throws Exception {
		
		if (root == null) {
			throw new Exception();			
		}

		// find the min between n1 and n2
		int x,y;
		if (n1 <= n2) {
			x = n1;
			y = n2;
		}
		else {
			x = n2;
			y = n1;
		}

		Node temp = root;
		int ancestor = root.data;

		while (temp != null) {
			if (temp.data >= x && temp.data <= y) {
				ancestor = temp.data;
				break;
			}
			else if (temp.data > y) {
				temp = temp.left;
			}
			else {
				temp = temp.right;
			}
		}
		return ancestor;
	}


	public void display() {
		inorderTraverseRecursively(root);
	}

	private void inorderTraverseRecursively(Node temp) {
		if (temp == null) {
			return;
		}
		inorderTraverseRecursively(temp.left);
		System.out.print(temp.data+" ");
		inorderTraverseRecursively(temp.right);
	}


	

}
