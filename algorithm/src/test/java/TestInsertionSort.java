import sort.InsertionSort;

import java.util.Arrays;

public class TestInsertionSort {
    public static void main(String[] args){
        Integer[] arr= {9,8,7,6,5,4,};

        InsertionSort.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
