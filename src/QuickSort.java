public class QuickSort {
    public static void quickSort(int[] array, int low, int high, int element) {
        if (low < high) {
            int pivot = partition(array, low, high);

            if (element < pivot) {
                // The element is less than the pivot.
                quickSort(array, low, pivot - 1, element);
            } else if (element > pivot) {
                // The element is bigger than the pivot.
                quickSort(array, pivot + 1, high, element);
            }
            // The pivot is the element.
        }
    }

    // Doing an awful sort of the array just based on whether, or not something
    // is less than the pivot. We don't care about the left and the right side
    // being perfectly sorted because as long as the element is less than or
    // greater than the pivot.
    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}
