package leetcode.twoSum_1;

import java.util.Arrays;

public class Solution {

	public static int[] twoSum(int[] nums, int target) {
		int[] output;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (j != i) {
					if (nums[i] + nums[j] == target) {
						output = new int[2];
						output[0] = i;
						output[1] = j;
						return output;
					}
				}
			}
		}
		output = new int[0];
		return output;
	}

	public static void main(String[] args) {
		int[] nums;
		int target;
		int[] outputTest;
		int[] outputFact;

		nums = new int[] { 2, 7, 11, 15 };
		target = 9;
		outputTest = new int[] { 0, 1 };
		outputFact = twoSum(nums, target);
		System.out.println(Arrays.compare(outputTest, outputFact) == 0);

		nums = new int[] { 3, 2, 4 };
		target = 6;
		outputTest = new int[] { 1, 2 };
		outputFact = twoSum(nums, target);
		System.out.println(Arrays.compare(outputTest, outputFact) == 0);

		nums = new int[] { 3, 3 };
		target = 6;
		outputTest = new int[] { 0, 1 };
		outputFact = twoSum(nums, target);
		System.out.println(Arrays.compare(outputTest, outputFact) == 0);
	}

}
