import java.util.Collections;
import java.util.PriorityQueue;

class Person {
    String name;
    int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
public class PriorityQueueDemo {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // min heap by default
        // add, offer add element to the queue

        pq.offer(10);
        pq.offer(20);
        pq.offer(30);

        System.out.println(pq.peek()); // peek to get top element (0(1) time complexity))

        System.out.println(pq.poll()); // poll get and remove top element (log n time complexity)

        System.out.println(pq.size()); // size of priority queue

        // Max Heap
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        maxHeap.offer(10);
        maxHeap.offer(20);
        maxHeap.offer(30);
        System.out.println(maxHeap.peek());
        System.out.println(maxHeap.remove()); // similar to poll but throws exception if the queue is empty
        System.out.println(maxHeap.size());

        System.out.println(maxHeap.isEmpty()); // to check if the priority queue is empty

        /*
                | Method      | Action         | On Failure       |
        | ----------- | -------------- | ---------------- |
        | `add(e)`    | Insert element | Throws Exception |
        | `offer(e)`  | Insert element | Returns `false`  |
        | `remove()`  | Remove head    | Throws Exception |
        | `poll()`    | Remove head    | Returns `null`   |
        | `element()` | Get head       | Throws Exception |
        | `peek()`    | Get head       | Returns `null`   |

        */

        // custom comparator
        PriorityQueue<Integer> customComparator = new PriorityQueue<>((a, b) -> b - a);
        customComparator.offer(10);
        customComparator.offer(20);
        customComparator.offer(30);
        System.out.println(customComparator.peek());
        System.out.println(customComparator.poll());
        System.out.println(customComparator.size());
        System.out.println(customComparator.isEmpty());

        // sort on basis of string length

        PriorityQueue<String> stringLengthComparator = new PriorityQueue<>((a, b) -> a.length() - b.length());
        stringLengthComparator.offer("apple");
        stringLengthComparator.offer("banana");
        stringLengthComparator.offer("cherry");
        stringLengthComparator.offer("date");
        System.out.println(stringLengthComparator.peek());
        System.out.println(stringLengthComparator.poll());
        System.out.println(stringLengthComparator.size());
        System.out.println(stringLengthComparator.isEmpty());   

        // Comparing on class objects
        // reverse order of age
        PriorityQueue<Person> personComparator = new PriorityQueue<>((a, b) -> {
            if(a.age == b.age){
                return a.name.compareTo(b.name); // sort in increasing order of name
            }
            return b.age - a.age;
        });
        personComparator.offer(new Person("John", 20));
        personComparator.offer(new Person("Jane", 21));
        personComparator.offer(new Person("Jim", 23));
        personComparator.offer(new Person("Jill", 23));
        System.out.println(personComparator.peek().age);
        System.out.println(personComparator.poll().name);
        System.out.println(personComparator.size());
        System.out.println(personComparator.isEmpty());


    }

}
