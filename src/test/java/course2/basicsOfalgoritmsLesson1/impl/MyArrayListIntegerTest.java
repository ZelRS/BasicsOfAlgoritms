package course2.basicsOfalgoritmsLesson1.impl;

import course2.basicsOfalgoritmsLesson1.IntegerList;
import course2.basicsOfalgoritmsLesson1.exceptions.InvalidIndexException;
import course2.basicsOfalgoritmsLesson1.exceptions.NullListException;
import course2.basicsOfalgoritmsLesson1.exceptions.StringNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static course2.basicsOfalgoritmsLesson1.MyArrayListTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class MyArrayListIntegerTest {

    private final IntegerList out = new MyArrayListInteger();
    private Integer[] expected;

    @BeforeEach
    public void setUp() {
        out.add(INTEGER1);
        out.add(INTEGER2);
        out.add(INTEGER3);
        out.add(INTEGER4);
        out.add(INTEGER5);
    }

    //    добавляется ли элемент по имени?
    @Test
    void shouldAddElementByItem() {
        expected = new Integer[]{INTEGER1, INTEGER2, INTEGER3, INTEGER4, INTEGER5, INTEGER6};
        out.add(INTEGER6);
        assertArrayEquals(expected, out.toArray());
    }

    //    добавляется ли элемент по индексу и имени?
    @Test
    void shouldAddElementByIndexAndItem() {
        expected = new Integer[]{INTEGER1, INTEGER6, INTEGER2, INTEGER3, INTEGER4, INTEGER5};
        out.add(1, INTEGER6);
        assertArrayEquals(expected, out.toArray());
    }

    //    выбрасывается ли исключение, если указан некорректный индекс?
    @Test
    void shouldThrowInvalidIndexExceptionWhileAdding() {
        assertThrows(InvalidIndexException.class, () -> out.add(5, INTEGER1));
        assertThrows(InvalidIndexException.class, () -> out.add(-10, INTEGER1));
    }

    //    добавляется ли новый элемент по индексу с затирванием старого?
    @Test
    void shouldSetElementInPlaceOfExisting() {
        expected = new Integer[]{INTEGER1, INTEGER6, INTEGER3, INTEGER4, INTEGER5};
        out.set(1, INTEGER6);
        assertArrayEquals(expected, out.toArray());
    }

    //    выбрасывается ли исключение, если указан некорректный индекс?
    @Test
    void shouldThrowInvalidIndexExceptionWhileSetting() {
        assertThrows(InvalidIndexException.class, () -> out.set(5, INTEGER1));
        assertThrows(InvalidIndexException.class, () -> out.set(-10, INTEGER1));
    }

    //    удаляется ли элеменет по имени?
    @Test
    void shouldRemoveElementByItem() {
        expected = new Integer[]{INTEGER1, INTEGER3, INTEGER4, INTEGER5};
        out.remove(INTEGER2);
        assertArrayEquals(expected, out.toArray());
    }

    //    выбрасывается ли исключение, если указанной строки нет в списке?
    @Test
    void shouldThrowStringNotFoundExceptionWhileRemoving() {
        assertThrows(StringNotFoundException.class, () -> out.remove(INTEGER6));
    }

    //    удаляется ли элеменет по индексу?
    @Test
    void shouldRemoveElementByIndex() {
        expected = new Integer[]{INTEGER1, INTEGER3, INTEGER4, INTEGER5};
        out.remove(1);
        assertArrayEquals(expected, out.toArray());
    }

    //    выбрасывается ли исключение, если указан некорректный индекс?
    @Test
    void shouldThrowInvalidIndexExceptionWhileRemoving() {
        assertThrows(InvalidIndexException.class, () -> out.remove(5));
        assertThrows(InvalidIndexException.class, () -> out.remove(-10));
    }

    //    есть ли в списке указанное имя?
    @Test
    void shouldReturnTrueIfContainsItem() {
        assertTrue(out.contains(INTEGER1));
        assertFalse(out.contains(INTEGER6));
    }

    //   есть ли в списке элемент(поиск смначала списка)?
    @Test
    void shouldReturnIndexOfItem() {
        assertEquals(0, out.indexOf(INTEGER1));
        assertEquals(-1, out.indexOf(INTEGER6));
    }

    //   есть ли в списке элемент(поиск  с конца списка)?
    @Test
    void shouldReturnIndexOfItemFromTheEnd() {
        assertEquals(3, out.lastIndexOf(INTEGER4));
        assertEquals(-1, out.lastIndexOf(INTEGER6));
    }

    //    получается ли элемент из списка по индексу?
    @Test
    void shouldGetItemByIndex() {
        assertEquals(INTEGER1, out.get(0));
    }

    //    выбрасывается ли исключение, если указан некорректный индекс?
    @Test
    void shouldThrowInvalidIndexExceptionWhileGetting() {
        assertThrows(InvalidIndexException.class, () -> out.get(5));
        assertThrows(InvalidIndexException.class, () -> out.get(-10));
    }

    //    правильно ли сравниваются списки?
    @Test
    void shouldReturnTrueIfListsEquals() {
        IntegerList expected = new MyArrayListInteger();
        expected.add(INTEGER1);
        expected.add(INTEGER2);
        expected.add(INTEGER3);
        expected.add(INTEGER4);
        expected.add(INTEGER5);
        assertTrue(out.equals(expected));
        assertEquals(expected.size(), out.size());
        for (int i = 0; i < out.size(); i++) {
            assertEquals(expected.get(i), out.get(i));
        }
    }

    //    являются ли размеры списков равными при определении Equals?
    @Test
    void shouldReturnFalseIfSizeOfListsNotEquals() {
        IntegerList expected = new MyArrayListInteger();
        expected.add(INTEGER1);
        expected.add(INTEGER5);
        expected.add(INTEGER3);
        expected.add(INTEGER4);
        assertNotEquals(expected.size(), out.size());
    }

    // выбрасывается ли исклбчение, если указан null при сравнении двух списков?
    @Test
    void shouldThrowNullListExceptionIfOtherListIsNull() {
        assertThrows(NullListException.class, () -> out.equals(null));
    }

    //    получается ли размер списка?
    @Test
    void shouldGetSizeOfList() {
        assertEquals(5, out.size());
    }

    // пустой ли список?
    @Test
    void shouldReturnTrueIfListIsEmpty() {
        out.clear();
        assertTrue(out.isEmpty());
    }

    // очищается ли список?
    @Test
    void shouldClearList() {
        out.clear();
        assertEquals(0, out.size());
        assertTrue(out.isEmpty());
    }

    //создается ли массив на основе списка?
    @Test
    void shouldTransformListToArray() {
        expected = new Integer[]{INTEGER1, INTEGER2, INTEGER3, INTEGER4, INTEGER5};
        assertArrayEquals(expected, out.toArray());
    }

    //создается ли отсортированный массив на основе списка?
    @Test
    void shouldTransformListToSortedArray() {
        expected = new Integer[]{INTEGER1, INTEGER2, INTEGER3, INTEGER4, INTEGER5};
        assertArrayEquals(expected, out.toSortedArray());
    }
}