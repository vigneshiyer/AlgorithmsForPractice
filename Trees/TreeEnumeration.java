import java.io.*;
/*
	Program to print the nth Catalyn numbers.
	T[0] = 1
	T[1] = 1
	T[2] = 2
	T[3] = T[2]*T[0] + T[1]*T[1] + T[0]*T[2]
	http://geeksquiz.com/enumeration-of-binary-trees/
*/
public class TreeEnumeration {
	public static void main (String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the number of Catalyn numbers required");
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i <= n; i++) {
			try {
				System.out.println(i+": "+getNumberOfTrees(i));
			}
			catch(Exception ex) {
				System.out.println("Invalid");
			}
		}
	}

	public static int getNumberOfTrees (int n) throws Exception {
		if (n == 0 || n == 1) {
			return 1;
		}
		if (n < 0) {
			throw new Exception();
		}
		int[] arr = new int[n+1];
		// base case
		arr[0] = 1;
		arr[1] = 1;
		arr[2] = 2;

		for (int i = 3; i < arr.length; i++) {
			int val = 0, pos = 0;
			for (int j = i-1; j >= 0; j--) {
				val += arr[j]*arr[pos];
				if (val < 0) {
					throw new Exception();
				}
				pos++;
			}
			arr[i] = val;			
		}
		return arr[n];		
	}
}
