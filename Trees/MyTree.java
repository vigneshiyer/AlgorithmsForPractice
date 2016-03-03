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

	public boolean deleteNode (int n) throws Exception {
		if (root == null) {
			throw new Exception();
		}

		Node temp = root;
		Node parent = null;

		while (temp != null) {
			if (n == temp.data) {
				break;
			}
			parent = temp;
			if (n < temp.data) {
				temp = temp.left;
			}
			else {
				temp = temp.right;
			}
		}

		if (temp == null) {
			return false;
		}

		if (temp.left == null && temp.right == null) {
			if (parent == null) {
				root = null;
				return true;
			}
			if (parent.left == temp) {
				parent.left = null;
			}
			else {
				parent.right = null;
			}
			return true;
		}

		if (temp.right == null) {
			if (parent == null) {
				root = root.left;
				return true;
			}
			if (parent.left == temp) {
				parent.left = temp.left;
			}
			else {
				parent.right = temp.left;
			}
			temp = null;
		}
		else if (temp.left == null) {
			if (parent == null) {
				root = root.right;
			}
			if (parent.left == temp) {
				parent.left = temp.right;
			}
			else {
				parent.right = temp.right;
			}
			temp = null;
		}
		else {
			//find min in right sub tree
			Node min = temp.right;
			while (min.left != null) {
				min = min.left;
			}
			temp.data = min.data;
			
			parent = temp;	
			temp = temp.right;

			while (temp != null) {
				if (temp.data == min.data) {
					break;
				}
				parent = temp;
				if (min.data < temp.data) {
					temp = temp.left;
				}
				else {
					temp = temp.right;
				}
			}

			if (temp.left == null && temp.right == null) {
				if (parent.left == temp) {
					parent.left = null;
				}
				else {
					parent.right = null;
				}
			}
			else{
				// min node in temp will never have a left child
				if (parent.left == temp) {
					parent.left = temp.right;
				}
				else {
					parent.right = temp.right;
				}
			}
	
		}
		return true;

	}
	
	/*
        1. Accepts a string to create a binary tree (not a BST)
        2. String is comma separated and follows a level order.
        3. If you want to skip a child, just put a #.
        4. The root cannot be #
        5. Example: 1,2,3,4,5
        6. Example: 1,2,#,#,5
        */
        public void createBT (String input) throws Exception {
                if (input == null || input.trim().length() == 0) {
                        throw new Exception ("The tree input is invalid");
                }

                String[] arr = input.split(",");
                
                if ("#".equals(arr[0])) {
                        throw new Exception ("Root cannot be null");
                }

                int val = Integer.parseInt(arr[0]);
                root = new Node(val);
                
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		boolean isLeft = true;
		int i = 1;
		Node itr = null;
		while ((itr = queue.poll()) != null) {
			for (int j = 0; j < 2 && i < arr.length; j++) {
				if (!("#".equals(arr[i]))) {
					Node newNode = new Node(Integer.parseInt(arr[i]));
					if (isLeft) {
						itr.left = newNode;
					}
					else {
						itr.right = newNode;
					}
					queue.add(newNode);
				}
				isLeft = !isLeft;
				i++;
			}
		} 
        }

	public void display() {
		inorderTraverseRecursively(root);
		System.out.println();
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
