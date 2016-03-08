import java.io.*;
import java.util.*;
public class FindIntersectionOfNArrays {

	public static void main (String args[]) throws IOException{
		
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		System.out.println("No. of arrays = ");
		int n = Integer.parseInt(br.readLine());
		System.out.println("Enter the arrays one by one with comma seperated values");
		
		List<int[]> list = new ArrayList<int[]>();
		for (int i = 0; i < n; i++) {
			System.out.println("Enter array "+(i+1));
			String[] temp = br.readLine().split(",");
			int[] arr = new int[temp.length];
			for (int j = 0; j < temp.length; j++) {
				arr[j] = Integer.parseInt(temp[j]);
			}
			list.add(arr);
		}

		/*
		int [] a = {1,1,2,3,4,5,6};
		int [] b = {3};
		int [] c = {4,5,1,2,1,7,3};
		int [] d = {1,1,1,2,2,3,7};
		int [] e = {2,1,2,4,4,1,3};

		List<int[]> list = new ArrayList<int[]>();
		list.add(b);list.add(a);list.add(c);list.add(d);list.add(e);
		*/

		int[] result = findIntersection(list);
		if (result == null) {
			System.out.println("No intersection found");
		}
		else {
			System.out.println("Intersection is ");
			for (int i = 0 ; i < result.length; i++) {
				if (i == result.length - 1) {
					System.out.println(result[i]);
				}
				else {
					System.out.print(result[i]+",");
				}
			}
		}
	}

	/*
		Receives a list of arrays whose intersection is to be found
	*/

	static int[] findIntersection (List<int[]> arrays) {
		if (arrays == null || arrays.size() == 0) {
			return null;
		}
		
		// this stores the first array's values in a hashmap. These entries will be used as a reference for the subsequent arrays.
		Map<Integer,Integer> firstArrMap = new HashMap<Integer,Integer>();
		int[] arrFirst = arrays.get(0);
		for (int i = 0; i < arrFirst.length; i++) {
			if (!firstArrMap.containsKey(arrFirst[i])) {
				firstArrMap.put(arrFirst[i],1);
			}
			else {
				firstArrMap.put(arrFirst[i],firstArrMap.get(arrFirst[i])+1);
			}
		}
		
		// create a list of hashmaps
		List<Map<Integer,Integer>> arrayMap = new ArrayList<Map<Integer,Integer>>();
		arrayMap.add(firstArrMap);

		// to keep track of the smallest hashmap containing the common elements.
		int minSize = firstArrMap.size();
		// initally the first array's hashmap is set to be min
		Map<Integer,Integer> minMap = firstArrMap;

		// start from the second array
		for (int i = 1; i < arrays.size(); i++) {
			int [] arr = arrays.get(i);
			arrayMap.add(new HashMap<Integer,Integer>());
			Map<Integer,Integer> tempMap = arrayMap.get(i);
			
			for (int j = 0; j < arr.length; j++) {
				// add only those keys that are present in the first hashmap
				if (firstArrMap.containsKey(arr[j])) {
					if (!tempMap.containsKey(arr[j])) {
						tempMap.put(arr[j],1);
					}
					else {
						tempMap.put(arr[j],arrayMap.get(i).get(arr[j])+1);
					}
				}
			}
			int size = tempMap.size();
			if (size == 0) {
				// no common elements
				break;
			}
			else if (size < minSize) {
				// update the min value
				minSize = size;
				minMap = tempMap;
			}
		}
		if (minSize == 0) {
			return null;
		}
		
		List<Integer> result = new ArrayList<Integer>();
		
		// traverse the smallest hashmap. The keys represent the members of the intersection result and the values represent their frequencies.
		for (Integer ele: minMap.keySet()) {
			int min = minMap.get(ele);
			for (int i = 0; i < arrayMap.size(); i++) {
				Map<Integer,Integer> temp = arrayMap.get(i);
				if (!temp.containsKey(ele)) {
					return null;
				}
				int val = temp.get(ele);
				if (val < min) {
					min = val;
				}
			}			
			for (int i = 0; i < min; i++) {
				result.add(ele);
			}
		}

		int[] finalresult = new int[result.size()];

		for (int i = 0; i < finalresult.length; i++) {
			finalresult[i] = result.get(i);
		}

		return finalresult;
	}

}
