import sort.choice_sort;

import java.util.Arrays;

public class TestChoice_sort {
    public static void main(String[] args){
        Integer[] arr={9,8,7,6,5,4,3,2,1};

        choice_sort.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
