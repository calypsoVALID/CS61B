import deque.ArrayDeque61B;

import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

    @Test
    public void addFirstTestBasic() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(8);
        deque.addFirst(4);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(1);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);
        assertThat(deque.toList()).containsExactly(6, 5, 4, 1, 3, 2, 4, 8).inOrder();
    }

    @Test
    public void addLastTestBasic() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addLast(8);
        deque.addLast(4);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(1);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        assertThat(deque.toList()).containsExactly(8, 4, 2, 3, 1, 4, 5, 6).inOrder();
    }

    @Test
    public void isEmptyTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        assertThat(deque.isEmpty()).isTrue();
        deque.addFirst(8);
        assertThat(deque.isEmpty()).isFalse();
        deque.addFirst(4);
        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(8);
        deque.addFirst(4);
        assertThat(deque.size()).isEqualTo(2);
        deque.removeFirst();
        assertThat(deque.size()).isEqualTo(1);
    }

    @Test
    public void removeFirstTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(8);
        deque.addFirst(4);
        deque.addFirst(2);
        deque.removeFirst();
        assertThat(deque.toList()).containsExactly(4, 8).inOrder();
    }

    @Test
    public void removeLastTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(8);
        deque.addFirst(4);
        deque.addFirst(2);
        deque.removeLast();
        assertThat(deque.toList()).containsExactly(2, 4).inOrder();
    }

    @Test
    public void getTest() {
        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
        deque.addFirst(8);
        deque.addFirst(4);
        deque.addFirst(2);
        deque.addFirst(3);
        assertThat(deque.get(0)).isEqualTo(3);
        assertThat(deque.get(1)).isEqualTo(2);
    }
}