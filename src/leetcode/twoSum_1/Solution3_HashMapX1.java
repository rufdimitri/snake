package leetcode.twoSum_1;

import java.util.Arrays;
import java.util.HashMap;

public class Solution3_HashMapX1 {

	public static int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		// HashMap fill and find complement:
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (map.containsKey(complement)) {
				int complIndex = map.get(complement);
				if (complIndex != i) {
					return new int[] { complIndex, i };
				}
			}
			map.put(nums[i], i);
		}
		return new int[] {};
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
