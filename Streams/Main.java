import java.util.*;
import java.util.stream.Stream;


public class Main{
    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Convert list to stream

        Stream<Integer> stream = nums.stream();

        // Streams don’t run until you add a terminal operation.

        stream.forEach(System.out::println);

        // streams are lazy, they don't run until you add a terminal operation.



    }
}