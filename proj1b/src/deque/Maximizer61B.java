package deque;
import java.util.Comparator;

public class Maximizer61B {
    /**
     * Returns the maximum element from the given iterable of comparables.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable  the Iterable of T
     * @return          the maximum element
     */
    public static <T extends Comparable<T>> T max(Iterable<T> iterable) {
        T max = null;
        for (T item : iterable){
            if (max == null || item.compareTo(max) > 0){
                max = item;
            }
        }
        return max;
    }

    /**
     * Returns the maximum element from the given iterable according to the specified comparator.
     * You may assume that the iterable contains no nulls.
     *
     * @param iterable  the Iterable of T
     * @param comp      the Comparator to compare elements
     * @return          the maximum element according to the comparator
     */
    public static <T> T max(Iterable<T> iterable, Comparator<T> comp) {
        T max = null;
        for (T item : iterable){
            if (max == null || comp.compare(max, item) > 0){
                max = item;
            }
        }
        return max;
    }

    public static void main(String[] args) {

         ArrayDeque61B<Integer> ad = new ArrayDeque61B<>();
         ad.addLast(5);
         ad.addLast(12);
         ad.addLast(37);
         ad.addLast(17);
         ad.addLast(23);
         System.out.println(max(ad));
    }
}
