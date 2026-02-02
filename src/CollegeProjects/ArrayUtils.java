public class ArrayUtils {

    /* 
    Create a utility class to analyze integer arrays. Implement the following methods:

    sumOfEvens(int[] nums): Return the sum of all even numbers in the array.
    countOccurrences(int[] nums, int target): Return how many times the target value appears in the array.

    Examples:

    sumOfEvens([1, 2, 3, 4, 5, 6]) = 12
    countOccurrences([2, 2, 2], 2) = 3

    Edge cases: empty array, null array, single element, negative numbers, all equal elements.
    */

    /* Algorithm for sumOfEvens:
    1. Check if the input array is null or empty. If so, return 0.
    2. Initialize a variable to hold the sum of even numbers.
    3. Loop through each element in the array
    4. For each element, check if it is even (i.e., divisible by 2).
    5. If it is even, add the element to the sum.
    6. After the loop is completed, return the sum.
    */
    public static int sumOfEvens(int[] nums) {
        if (nums == null || nums.length == 0) { 
            return 0;
        }
        int sum = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                sum += num;
            }
        }
        return sum;
    }


    /* Algorithm for countOccurrences:
    1. Check if the input array is null or empty. If so, return 0.
    2. Initialize a variable to hold the count of occurrences.
    3. Loop through each element in the array.
    4. For each element, check if the element is equal to the target value.
    5. If it does, increment the count by 1.
    6. After the loop is completed, return the count.
    */
public static int countOccurences(int[]nums, int target) {
    if (nums == null || nums.length == 0) {
            return 0;
    }
    int count = 0;
    for (int num : nums) {
        if  (num == target) {
            count++;
        }
    }
    return count;
    }


    
    
    public static void main(String[] args) throws Exception {
        System.out.println(sumOfEvens(new int[]{1, 2, 3, 4, 5, 6})); // Output: 12
        System.out.println(countOccurences(new int[]{2, 2, 2}, 2)); // Output: 3

        // Additional test cases
        System.out.println(sumOfEvens(new int[]{})); // Output: 0
    }
}