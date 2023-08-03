package rd.bubbleSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BubbleSort {
    static class Implementation {
        static List<Implementation> implementations = new ArrayList<>();
        private String name;
        private String description;

        static {
            implementations.add(new Implementation("Bubble Sort", "Standard implementation, no optimizations") {
                public void sort(int[] array) {
                    int buffer;
                    for (int i = 0; i < array.length; i++) {
                        for (int j = 0; j < array.length - 1; j++) {
                            if (array[j] > array[j + 1]) {
                                buffer = array[j];
                                array[j] = array[j + 1];
                                array[j + 1] = buffer;
                            }
                        }
                    }
                }
            });

            implementations.add(new Implementation("BS Optimize #1", "Break if no changes to array were made") {
                public void sort(int[] array) {
                    int buffer;
                    boolean changed = true;
                    while (changed) {
                        changed = false;
                        for (int j = 0; j < array.length - 1; j++) {
                            if (array[j] > array[j + 1]) {
                                buffer = array[j];
                                array[j] = array[j + 1];
                                array[j + 1] = buffer;
                                changed = true;
                            }
                        }
                    }
                }
            });

            implementations.add(new Implementation("BS Optimize #2",
                    "Loop only up to last sorted number (after every iteration"
                            + " the biggest number is moved to the end and we dont need to compare it in next iterations)") {
                public void sort(int[] array) {
                    int buffer;
                    for (int i = 0; i < array.length; i++) {
                        for (int j = 0; j < array.length - 1 - i; j++) {
                            if (array[j] > array[j + 1]) {
                                buffer = array[j];
                                array[j] = array[j + 1];
                                array[j + 1] = buffer;
                            }
                        }
                    }
                }
            });

            implementations.add(new Implementation("BS Optimize #1 & #2",
                    "Optimization #1: Break if no changes to array were made \n" +
                            "Optimization #2: Loop only up to last sorted number (after every iteration " +
                            " the biggest number is moved to the end and we dont need to compare it in next" + //
                            " iterations)") {

                public void sort(int[] array) {
                    int buffer;
                    boolean changed = true;
                    for (int i = 0; i < array.length; i++) {
                        changed = false;
                        for (int j = 0; j < array.length - 1 - i; j++) {
                            if (array[j] > array[j + 1]) {
                                buffer = array[j];
                                array[j] = array[j + 1];
                                array[j + 1] = buffer;
                                changed = true;
                            }
                        }
                        if (!changed) {
                            break;
                        }
                    }
                }
            });

        }

        Implementation(String name) {
            this.name = name;
        }

        Implementation(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return this.name;
        }

        public String getDescription() {
            return this.description;
        }

        public void sort(int[] array) {
            throw new RuntimeException("Method is not implemented.");
        };

    }

    public static void main(String[] args) {
		runAllTests();
		testInt(0, 1000, false, ;
		testInt(0, 1000, true, ;
    }

	static void runAllTests() {
		testGenerateArray();
		testSort();
	}

	/**
	 * 
	 * @param valueMin
	 * @param valueMax
	 * @param randomValuesStep: "sorted" for sorted array; "unsorted" for unsorted
	 *                          array; a number (e.g. "10") for sorted array, but
	 *                          every 10th element will be a random number
	 *                          (unsorted). See generateArray() method
	 */
	static int[] generateArray(int length, int valuesMin, int valuesMax, String randomValuesStep) {
		int[] array = new int[length];
        
		arrayFill(array, 1, valuesMin, valuesMax);
		
		if (randomValuesStep.equals("sorted")) {
			sort(array);
		}

		// fill every randomValuesStep value with a random number
		int step = 0;
		try {
			step = Integer.parseInt(randomValuesStep);
		} catch (NumberFormatException e) {
			// do nothing
		}
		if (step != 0) {
			arrayFill(array, step, valuesMin, valuesMax);
		}

        return array;
    }
    
	static void arrayFill(int[] array, int step, int valueMin, int valueMax) {
		Random rand = new Random();
		for (int i = 0; i < array.length; i++) {
			if (i % step == 0) {
				array[i] = rand.nextInt(valueMin, valueMax);
			}
		}
	}

	static void testGenerateArray() {
		int[] array = generateArray(10, 0, 1000, "unsorted");
		if (array.length != 10) {
			throw new RuntimeException("testGenerateArray failed");
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] < 0 || array[i] > 1000) {
				throw new RuntimeException("testGenerateArray failed");
			}
		}

		// test sorted array
		int[] array2 = generateArray(10, 0, 1000, "sorted");
		if (array2.length != 10) {
			throw new RuntimeException("testGenerateArray failed");
		}
		for (int i = 0; i < array2.length; i++) {
			if (array2[i] < 0 || array2[i] > 1000) {
				throw new RuntimeException("testGenerateArray failed");
			}
		}
		if (!isSorted(array2)) {
			throw new RuntimeException("testGenerateArray failed");
		}
		System.out.println("testGenerateArray ok");
	}

	static void sort(int[] array) {
		int minI;
		int buffer;
		for (int k = 0; k < array.length; k++) {
			minI = k;
			for (int i = k; i < array.length; i++) {
				if (array[i] < array[minI]) {
					minI = i;
				}
			}
			buffer = array[k];
			array[k] = array[minI];
			array[minI] = buffer;
		}
	}

	static void testSort() {
		int[] array1 = { 5, 3, 1, 2, 6, 7 };
		sort(array1);
		if (!isSorted(array1)) {
			throw new RuntimeException("testSort failed");
		}
		System.out.println("testSort ok");
	}

	static boolean isSorted(int[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1]) {
				return false;
			}
		}
		return true;
	}

    static void displayArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
	}

	/**
	 * 
	 * @param valueMin
	 * @param valueMax
	 * @param randomValuesStep: "sorted" for sorted array; "unsorted" for unsorted
	 *                          array; a number (e.g. "10") for sorted array, but
	 *                          every 10th element will be a random number
	 *                          (unsorted). See generateArray() method
	 */
	static void testInt(int valueMin, int valueMax, String randomValuesStep) { // TODO
        int[] intArray = {};
        int[] testArray = {};
        long startTime, endTime;

        // int[] arrayLengthsTestValues = { 1000, 10000, 50000, 60000, 70000, 80000,
        // 90000, 100000, 110000, 120000, 130000,
        // 140000, 150000, 160000, 170000 };
        int[] arrayLengthsTestValues = { 1000, 11000, 21000, 31000, 41000, 51000, 61000, 71000, 81000, 91000, 101000 };

        System.out.println("Implementations: ");
        for (Implementation impl : Implementation.implementations) {
            System.out.println(impl.getName() + ": " + impl.getDescription());
        }

        for (int r = 0; r < arrayLengthsTestValues.length + 1; r++) { // +1 for first row with captions
            if (r > 0) {
				intArray = generateArray(arrayLengthsTestValues[r - 1], valueMin, valueMax, randomValuesStep);
            }
            for (int c = 0; c < (Implementation.implementations.size() + 1); c++) { // +1 for first column with
                                                                                    // arrayLengths
                if (r == 0 && c == 0) {
                    System.out.format("%20s", "Amount of elements");
                } else if (r > 0 && c == 0) {
                    System.out.format("%20d", arrayLengthsTestValues[r - 1]);
                } else if (c > 0) {
                    Implementation impl = Implementation.implementations.get(c - 1);
                    if (r == 0) {
                        System.out.format("%25s", impl.getName());
                    } else {
                        testArray = Arrays.copyOf(intArray, intArray.length);
                        startTime = System.currentTimeMillis();
                        impl.sort(testArray);
                        endTime = System.currentTimeMillis();
                        System.out.format("%25d", endTime - startTime);
                    }
                }
                System.out.print(",");
            }
            System.out.println();
        }
    }

}
