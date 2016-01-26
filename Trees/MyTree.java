package Trees;

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
