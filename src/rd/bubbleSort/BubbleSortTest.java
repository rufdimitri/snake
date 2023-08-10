package rd.bubbleSort;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import rd.bubbleSort.BubbleSort.SortParam;

class BubbleSortTest {

	@Test
	void testGenerateArray() {
		int[] array = BubbleSort.generateArray(10, 0, 1000, new SortParam(false));
		if (array.length != 10) {
			fail("Array has wrong length");
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] < 0 || array[i] > 1000) {
				fail("Array values range out of bound");
			}
		}

		// test sorted array
		int[] array2 = BubbleSort.generateArray(10, 0, 1000, new SortParam(true));
		if (array2.length != 10) {
			fail("Array has wrong length");
		}
		for (int i = 0; i < array2.length; i++) {
			if (array2[i] < 0 || array2[i] > 1000) {
				fail("Array values range out of bound");
			}
		}
		if (!BubbleSort.isSorted(array2)) {
			fail("Array is not sorted");
		}
	}

	@Test
	void testArrayFill() {
		int[] array1 = { -1, -1, -1 };
		int min = 10;
		int max = 15;
		BubbleSort.arrayFill(array1, 1, 10, 15);
		for (int i = 0; i < array1.length; i++) {
			if (array1[i] < min || array1[i] > max) {
				fail("Array values range out of bound");
			}
		}
	}

	@Test
	void testSort() {
		int[] array1 = { 5, 3, 1, 2, 6, 7 };
		int[] array1Sorted = { 1, 2, 3, 5, 6, 7 };
		BubbleSort.sort(array1);
		Assert.assertTrue("Array is not sorted or damaged", Arrays.equals(array1, array1Sorted));
	}

	@Test
	void testIsSorted() {
		int[] array1 = { 1, 2, 5, 7, 10 }; // sorted
		int[] array2 = { 2, 1, 5, 7, 10 }; // unsorted
		int[] array3 = { -10, -5, 0, 1, 5, 7, 10 }; // sorted with neg values
		int[] array4 = { -5, -10, 0, 1, 5, 7, 10 }; // unsorted with neg values
		Assert.assertTrue("Should be true (sorted)", BubbleSort.isSorted(array1));
		Assert.assertFalse("Should be false (unsorted)", BubbleSort.isSorted(array2));
		Assert.assertTrue("Should be true (sorted)", BubbleSort.isSorted(array3));
		Assert.assertFalse("Should be false (unsorted)", BubbleSort.isSorted(array4));
	}

}
