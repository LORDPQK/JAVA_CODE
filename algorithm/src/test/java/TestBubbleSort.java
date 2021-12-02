import sort.BubbleSort;
import java.util.Arrays;

public class TestBubbleSort {

    public static void main(String[] args){
        Integer[] arr = {9,8,7,6,5,4,3,2,1};

        BubbleSort.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
