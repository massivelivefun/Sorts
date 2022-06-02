import java.util.Arrays;

public class MedianOfMedians {
    public static int ithItem(int[] array, int i, int n) {
        int answer, medianOfMedians, j = 0, k = 0, l;
        int[] medians, smaller, larger;

        // If n is small, just sort it and return.
        if (n < 50) {
            // From 0 to n because the Array memory allocation was lazy on my
            // part. If I just sorted it then there would be a ton of leading
            // zeros which would mess up the whole process because everything
            // would be shifted over to the right by the number of zeros that
            // was original in the array in the last stack call's array.
            Arrays.sort(array, 0, n);
            answer = array[i];
        } else {
            // Make some arrays to put stuff in them.
            medians = new int[n/5];
            smaller = new int[n];
            larger = new int[n];

            // Fill up the medians array of each five elements.
            for (l = 0; l < n/5; l++) {
                medians[l] = median(
                    array[5*l],
                    array[5*l+1],
                    array[5*l+2],
                    array[5*l+3],
                    array[5*l+4]
                );
            }

            // Find the median of medians.
            medianOfMedians = ithItem(medians, n/10, n/5);

            // Use the median of medians method to sort the data.
            for (l = 0; l < n; l++) {
                if (array[l] <= medianOfMedians) {
                    smaller[j++] = array[l];
                } else {
                    larger[k++] = array[l];
                }
            }

            // Okay which side do we go down?
            if (i < j) {
                // The answer is in the smaller side.
                answer = ithItem(smaller, i, j);
            } else {
                // The answer is in the larger side.
                answer = ithItem(larger, i - j, k);
            }
        }
        return answer;
    }

    // Find the median of any five numbers in six comparisons.
    private static int median(int a, int b, int c, int d, int e) {
        int temp;

        if (a < b) {
            temp = a;
            a = b;
            b = temp;
        }

        if (c < d) {
            temp = c;
            c = d;
            d = temp;
        }

        if (a < c) {
            temp = a;
            a = c;
            c = temp;

            temp = b;
            b = d;
            d = temp;
        }

        if (b < e) {
            temp = b;
            b = e;
            e = temp;
        }

        if (b > c) {
            return Math.max(c, e);
        }

        return Math.max(b, d);
    }
}
