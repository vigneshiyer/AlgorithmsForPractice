package DP;

import java.util.*;
import java.io.*;

public class CoinChange {
	
	static int getSumUsingMinimumCoins (int sum, int[] change) {
		if (change.length == 0) {
			return 0;
		}

		int [] result = new int[sum+1];
		
		// base case
		result[0] = 0;

		for (int i = 1; i < result.length; i++) {
			int min = Integer.MAX_VALUE;
			for (int ele : change) {
				if (ele > i) {
					continue;
				}
				int val = i - ele;
				if ((result[val]+1) < min) {
					min = result[val]+1;
				}
			}
			result[i] = min;
		}
		return result[result.length-1];
	}
}
