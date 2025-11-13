import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortingList {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 1, 9, 2, 7);
        Collections.sort(numbers);
        System.out.println("Sorted: " + numbers);
    }
}
