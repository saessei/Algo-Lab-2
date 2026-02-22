import java.util.Comparator;

public class BinarySearchDeluxe {

    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new Error("Arguments cannot be null");
        }

        int low = 0;
        int high = a.length - 1;
        int firstIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = comparator.compare(key, a[mid]);

            if (cmp < 0) {
                high = mid - 1;
            } else if (cmp > 0) {
                low = mid + 1;
            } else {
                firstIndex = mid;
                high = mid - 1;
            }
        }
        return firstIndex;
    }

    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new Error("Arguments cannot be null");
        }

        int low = 0;
        int high = a.length - 1;
        int lastIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = comparator.compare(key, a[mid]);

            if (cmp < 0) {
                high = mid - 1;
            } else if (cmp > 0) {
                low = mid + 1;
            } else {
                lastIndex = mid;
                low = mid + 1;
            }
        }
        return lastIndex;
    }

    // Unit testing
    public static void main(String[] args) {
        // ------- TESTING WITH STRING ARRAYS
        System.out.println("Testing with String arrays:");
        String[] strs = {"A", "A", "C", "G", "G", "G", "T", "T", "T", "T"};
        Comparator<String> comp = String.CASE_INSENSITIVE_ORDER;

        // ------- Test for firstIndexOf
        // Test results in positive number 
        int firstG = BinarySearchDeluxe.firstIndexOf(strs, "G", comp);
        System.out.println("First 'G' index: " + firstG);  
        // Test results in negative number
        int firstZ = BinarySearchDeluxe.firstIndexOf(strs, "Z", comp);
        System.out.println("First 'Z' index: " + firstZ); 
        // Results in 0 
        int firstA = BinarySearchDeluxe.firstIndexOf(strs, "A", comp);
        System.out.println("First 'A' index: " + firstA); 

        // ------- Test for lastIndexOf
        // Test results in positive number
        int lastG = BinarySearchDeluxe.lastIndexOf(strs, "G", comp);
        System.out.println("Last 'G' index: " + lastG);
        // Test results in negative number
        int lastZ = BinarySearchDeluxe.lastIndexOf(strs, "Z", comp);    
        System.out.println("Last 'Z' index: " + lastZ);
        // Results in 1
        int lastA = BinarySearchDeluxe.lastIndexOf(strs, "A", comp);
        System.out.println("Last 'A' index: " + lastA);

        // ------- TESTING WITH INTEGER ARRAYS
        System.out.println("\nTesting with Integer arrays:");
        Integer[] nums = {1, 2, 2, 3, 4, 4, 5};
        Comparator<Integer> intComp = Integer::compareTo;

        // ------- Test for firstIndexOf
        // Test results in positive number 
        int first2 = BinarySearchDeluxe.firstIndexOf(nums, 2, intComp);
        System.out.println("First '2' index: " + first2);  
        // Test results in negative number
        int first6 = BinarySearchDeluxe.firstIndexOf(nums, 6, intComp);
        System.out.println("First '6' index: " + first6); 
        // Results in 0 
        int first1 = BinarySearchDeluxe.firstIndexOf(nums, 1, intComp);
        System.out.println("First '1' index: " + first1); 

        // ------- Test for lastIndexOf
        // Test results in positive number
        int last2 = BinarySearchDeluxe.lastIndexOf(nums, 2, intComp);
        System.out.println("Last '2' index: " + last2);
        // Test results in negative number
        int last6 = BinarySearchDeluxe.lastIndexOf(nums, 6, intComp);    
        System.out.println("Last '6' index: " + last6);
        // Results in 0
        int last1 = BinarySearchDeluxe.lastIndexOf(nums, 1, intComp);
        System.out.println("Last '1' index: " + last1);

        // Test with empty array
        Integer[] empty = {};
        System.out.println("\nTesting with empty array:");
        System.out.println("First in empty: " + BinarySearchDeluxe.firstIndexOf(empty, 1, intComp)); // Should be -1
        System.out.println("Last in empty: " + BinarySearchDeluxe.lastIndexOf(empty, 1, intComp)); // Should be -1
    }
}
