import deque.ArrayDeque61B;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import deque.ArrayDeque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {
    private ArrayDeque61B<Integer> deque;

    @BeforeEach
    public void setup() {
        deque = new ArrayDeque61B<>();
    }

    @Test
    public void testAddFirstAndGet() {
        deque.addFirst(10);
        deque.addFirst(20);
        deque.addFirst(30);
        assertEquals(3, deque.size());
        assertEquals(30, deque.get(0));
        assertEquals(20, deque.get(1));
        assertEquals(10, deque.get(2));
    }

    @Test
    public void testAddLastAndGet() {
        deque.addLast(5);
        deque.addLast(15);
        deque.addLast(25);
        assertEquals(3, deque.size());
        assertEquals(5, deque.get(0));
        assertEquals(15, deque.get(1));
        assertEquals(25, deque.get(2));
    }

    @Test
    public void testAddFirstAddLastMixed() {
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        assertEquals(4, deque.size());
        assertEquals(3, deque.get(0));
        assertEquals(1, deque.get(1));
        assertEquals(2, deque.get(2));
        assertEquals(4, deque.get(3));
    }

    @Test
    public void testRemoveFirst() {
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);
        assertEquals(10, deque.removeFirst());
        assertEquals(20, deque.removeFirst());
        assertEquals(30, deque.removeFirst());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testRemoveLast() {
        deque.addFirst(10);
        deque.addFirst(20);
        deque.addFirst(30);
        assertEquals(10, deque.removeLast());
        assertEquals(20, deque.removeLast());
        assertEquals(30, deque.removeLast());
        assertTrue(deque.isEmpty());
    }

    @Test
    public void testResizeUp() {
        // Add more than initial capacity (8) to force resize
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }
        assertEquals(20, deque.size());
        for (int i = 0; i < 20; i++) {
            assertEquals(i, deque.get(i));
        }
    }

    @Test
    public void testResizeDown() {
        // Add 20, then remove many to force shrink
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }
        for (int i = 0; i < 18; i++) {
            deque.removeFirst();
        }
        // Should have shrunk internally
        assertEquals(2, deque.size());
        assertEquals(18, deque.get(0));
        assertEquals(19, deque.get(1));
    }

    @Test
    public void testToList() {
        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(0);
        var list = deque.toList();
        assertArrayEquals(new Integer[]{0, 1, 2}, list.toArray(new Integer[0]));
    }

    @Test
    public void testGetInvalidIndex() {
        deque.addLast(5);
        assertNull(deque.get(-1));
        assertNull(deque.get(1));
    }

    @Test
    public void testRemoveOnEmpty() {
        assertNull(deque.removeFirst());
        assertNull(deque.removeLast());
    }

//    @Test
//    public void addFirstTestBasic() {
//        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
//        deque.addFirst(8);
//        deque.addFirst(4);
//        deque.addFirst(2);
//        deque.addFirst(3);
//        deque.addFirst(1);
//        deque.addFirst(4);
//        deque.addFirst(5);
//        deque.addFirst(6);
//        assertThat(deque.toList()).containsExactly(6, 5, 4, 1, 3, 2, 4, 8).inOrder();
//    }
//
//    @Test
//    public void addLastTestBasic() {
//        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
//        deque.addLast(8);
//        deque.addLast(4);
//        deque.addLast(2);
//        deque.addLast(3);
//        deque.addLast(1);
//        deque.addLast(4);
//        deque.addLast(5);
//        deque.addLast(6);
//        assertThat(deque.toList()).containsExactly(8, 4, 2, 3, 1, 4, 5, 6).inOrder();
//    }
//
//    @Test
//    public void isEmptyTest() {
//        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
//        assertThat(deque.isEmpty()).isTrue();
//        deque.addFirst(8);
//        assertThat(deque.isEmpty()).isFalse();
//        deque.addFirst(4);
//        assertThat(deque.isEmpty()).isFalse();
//    }
//
//    @Test
//    public void sizeTest() {
//        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
//        deque.addFirst(8);
//        deque.addFirst(4);
//        assertThat(deque.size()).isEqualTo(2);
//        deque.removeFirst();
//        assertThat(deque.size()).isEqualTo(1);
//    }
//
//    @Test
//    public void removeFirstTestBasic() {
//        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
//        deque.addFirst(8);
//        deque.addFirst(4);
//        deque.addFirst(2);
//        deque.removeFirst();
//        assertThat(deque.toList()).containsExactly(4, 8).inOrder();
//    }
//
//    @Test
//    public void removeLastTestBasic() {
//        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
//        deque.addFirst(8);
//        deque.addFirst(4);
//        deque.addFirst(2);
//        deque.removeLast();
//        assertThat(deque.toList()).containsExactly(2, 4).inOrder();
//    }
//
//    @Test
//    public void getTest() {
//        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
//        deque.addFirst(8);
//        deque.addFirst(4);
//        deque.addFirst(2);
//        deque.addFirst(3);
//        assertThat(deque.get(0)).isEqualTo(3);
//        assertThat(deque.get(1)).isEqualTo(2);
//    }
//
//    @Test
//    public void addFirstTest() {
//        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
//        deque.addFirst(8);
//        deque.addFirst(4);
//        deque.addFirst(2);
//        deque.addFirst(3);
//        deque.addFirst(1);
//        deque.addFirst(4);
//        deque.addFirst(5);
//        deque.addFirst(6);
//        deque.addFirst(7);
//        deque.addFirst(8);
//        assertThat(deque.toList()).containsExactly(8, 7, 6, 5, 4, 1, 3, 2, 4, 8).inOrder();
//    }
//
//    @Test
//    public void addLastTest() {
//        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
//        deque.addLast(8);
//        deque.addLast(4);
//        deque.addLast(2);
//        deque.addLast(3);
//        deque.addLast(1);
//        deque.addLast(4);
//        deque.addLast(5);
//        deque.addLast(6);
//        deque.addLast(7);
//        deque.addLast(8);
//        deque.addLast(9);
//        assertThat(deque.toList()).containsExactly(8, 4, 2, 3, 1, 4, 5, 6, 7, 8, 9).inOrder();
//    }
//
//    @Test
//    public void resizeDownTest() {
//
//    }
//
//    @Test
//    public void removeFirstTest() {
//        ArrayDeque61B<Integer> deque = new ArrayDeque61B<>();
//        deque.addFirst(8);
//        deque.addFirst(4);
//        deque.addFirst(2);
//        deque.addFirst(3);
//        deque.addFirst(1);
//        deque.addFirst(4);
//        deque.addFirst(5);
//        deque.addFirst(6);
//    }
}