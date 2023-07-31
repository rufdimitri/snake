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
        displayArray(generateArray(100, 50, 100, true));
        // testInt(5, 0, 1000);
    }

    static int[] generateArray(int length, int valuesMin, int valuesMax, boolean sorted) {
        int[] array = new int[length];
        Random rand = new Random();
        int scopeMin;
        int scopeMax;
        int scopeCount = 0;
        if (length >= (valuesMax - valuesMin)) {
            scopeCount = 5;
        } else {
            scopeCount = length;
        }
        int scopeSize = (valuesMax - valuesMin) / scopeCount;
        for (int i = 0; i < array.length; i++) {
            if (sorted) {
                scopeMin = scopeSize * i + valuesMin;
                scopeMax = scopeMin + scopeSize;
                array[i] = rand.nextInt(scopeMin, scopeMax); // TODO Fix this random
            } else {
                array[i] = rand.nextInt(valuesMin, valuesMax);
            }
        }
        return array;
    }

    static void displayArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    static void testInt(int length, int valueMin, int valueMax) {
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
                intArray = generateArray(arrayLengthsTestValues[r - 1], valueMin, valueMax, true);
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
