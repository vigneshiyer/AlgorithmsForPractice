package Trees;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class MyTreeHelper {
	/*
	1. Accepts n as the required number of nodes in the tree. If n <= 0 then throw exception
	2. Creates a random number as a multiplier to get the max value allowed in the BST
	3. Then creates n random numbers for n nodes of BST. Duplicates are prevented by using a set.
	4. Returns the root  
	*/
	public static MyTree createBST (int n) throws Exception {
		
		if (n <= 0) {
			throw new Exception();
		}
		Random rand = new Random(20);
		int multiplier = rand.nextInt(40);
		rand.setSeed(5);
		int max = n*multiplier;
		Set <Integer> set = new HashSet<Integer>();
		MyTree tree = new MyTree();
		for (int i = 0; i < n; i++) {
			while (true) {
				int num = rand.nextInt(max);
				if (!set.contains(num)) {
					set.add(num);
					tree.insertNode(num);
					break;
				}
			}
		}
		return tree;
	}


}
