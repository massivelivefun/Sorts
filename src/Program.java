import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;

public class Program {
    public static void main(String[] args) {
        // Get the input file from the end user.
        Scanner scr = new Scanner(System.in);
        String fileName = null;
        System.out.print("Enter the input file relative to the pwd: ");
        try {
            fileName = scr.nextLine();
        } catch (IllegalStateException | NoSuchElementException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // Read from the input file.
        try {
            scr = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        int element = 0;
        int size = 0;
        try {
            // Get the smallest ith element that we are looking for from the
            // input file.
            element = scr.nextInt();
            // Get the size of the array from the input file.
            size = scr.nextInt();
        } catch (IllegalStateException | NoSuchElementException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // Make the three arrays to compare to each other.
        int[] standardArray = new int[size];
        int[] quickSortArray = new int[size];
        int[] medianOfMediansArray = new int[size];

        // Populate the arrays with the remaining numbers from the input file.
        int number = 0;
        for (int i = 0; i < size; i++) {
            try {
                number = scr.nextInt();
            } catch (IllegalStateException | NoSuchElementException e) {
                System.err.println(e.getMessage());
                System.exit(1);
            }
            standardArray[i] = number;
            quickSortArray[i] = number;
            medianOfMediansArray[i] = number;
        }

        scr.close();

        // Use the Java API Arrays sort.
        Arrays.sort(standardArray);

        // Use quicksort to put the median in the correct position.
        QuickSort.quickSort(
            quickSortArray, 0, quickSortArray.length - 1, element);

        // Find the median through the median of medians method.
        int medianOfMedians = MedianOfMedians.ithItem(
            medianOfMediansArray, element, medianOfMediansArray.length);

        System.out.println("Sort finds: " + standardArray[element]);
        System.out.println("Quicksort finds: " + quickSortArray[element]);
        System.out.println("Median of medians finds: " + medianOfMedians);
    }
}
