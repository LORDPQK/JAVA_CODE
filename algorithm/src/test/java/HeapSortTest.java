import heap.HeapSort;

import java.util.Arrays;

public class HeapSortTest {
    public static void main(String[] args) {
        String[] arr ={"D","C","B","A"};
        HeapSort.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
