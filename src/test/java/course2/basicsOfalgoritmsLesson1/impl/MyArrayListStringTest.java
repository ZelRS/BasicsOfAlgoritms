package course2.basicsOfalgoritmsLesson1.impl;

import course2.basicsOfalgoritmsLesson1.StringList;
import course2.basicsOfalgoritmsLesson1.exceptions.InvalidIndexException;
import course2.basicsOfalgoritmsLesson1.exceptions.NullListException;
import course2.basicsOfalgoritmsLesson1.exceptions.StringNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static course2.basicsOfalgoritmsLesson1.MyArrayListTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

class MyArrayListStringTest {

    private final StringList out = new MyArrayListString();
    private String[] expected;

    @BeforeEach
    public void setUp() {
        out.add(STRING1);
        out.add(STRING2);
        out.add(STRING3);
        out.add(STRING4);
        out.add(STRING5);
    }

    //    добавляется ли элемент по имени?
    @Test
    void shouldAddElementByItem() {
        expected = new String[]{STRING1, STRING2, STRING3, STRING4, STRING5, STRING6};
        out.add(STRING6);
        assertArrayEquals(expected, out.toArray());
    }

    //    добавляется ли элемент по индексу и имени?
    @Test
    void shouldAddElementByIndexAndItem() {
        expected = new String[]{STRING1, STRING6, STRING2, STRING3, STRING4, STRING5};
        out.add(1, STRING6);
        assertArrayEquals(expected, out.toArray());
    }

    //    выбрасывается ли исключение, если указан некорректный индекс?
    @Test
    void shouldThrowInvalidIndexExceptionWhileAdding() {
        assertThrows(InvalidIndexException.class, () -> out.add(5, STRING1));
        assertThrows(InvalidIndexException.class, () -> out.add(-10, STRING1));
    }

    //    добавляется ли новый элемент по индексу с затирванием старого?
    @Test
    void shouldSetElementInPlaceOfExisting() {
        expected = new String[]{STRING1, STRING6, STRING3, STRING4, STRING5};
        out.set(1, STRING6);
        assertArrayEquals(expected, out.toArray());
    }

    //    выбрасывается ли исключение, если указан некорректный индекс?
    @Test
    void shouldThrowInvalidIndexExceptionWhileSetting() {
        assertThrows(InvalidIndexException.class, () -> out.set(5, STRING1));
        assertThrows(InvalidIndexException.class, () -> out.set(-10, STRING1));
    }

    //    удаляется ли элеменет по имени?
    @Test
    void shouldRemoveElementByItem() {
        expected = new String[]{STRING1, STRING3, STRING4, STRING5};
        out.remove(STRING2);
        assertArrayEquals(expected, out.toArray());
    }

    //    выбрасывается ли исключение, если указанной строки нет в списке?
    @Test
    void shouldThrowStringNotFoundExceptionWhileRemoving() {
        assertThrows(StringNotFoundException.class, () -> out.remove(STRING6));
    }

    //    удаляется ли элеменет по индексу?
    @Test
    void shouldRemoveElementByIndex() {
        expected = new String[]{STRING1, STRING3, STRING4, STRING5};
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
        assertTrue(out.contains(STRING1));
        assertFalse(out.contains(STRING6));
    }

    //   есть ли в списке элемент(поиск смначала списка)?
    @Test
    void shouldReturnIndexOfItem() {
        assertEquals(0, out.indexOf(STRING1));
        assertEquals(-1, out.indexOf(STRING6));
    }

    //   есть ли в списке элемент(поиск  с конца списка)?
    @Test
    void shouldReturnIndexOfItemFromTheEnd() {
        assertEquals(3, out.lastIndexOf(STRING4));
        assertEquals(-1, out.lastIndexOf(STRING6));
    }

    //    получается ли элемент из списка по индексу?
    @Test
    void shouldGetItemByIndex() {
        assertEquals(STRING1, out.get(0));
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
        StringList expected = new MyArrayListString();
        expected.add(STRING1);
        expected.add(STRING2);
        expected.add(STRING3);
        expected.add(STRING4);
        expected.add(STRING5);
        assertTrue(out.equals(expected));
        assertEquals(expected.size(), out.size());
        for (int i = 0; i < out.size(); i++) {
            assertEquals(expected.get(i), out.get(i));
        }
    }

    //    являются ли размеры списков равными при определении Equals?
    @Test
    void shouldReturnFalseIfSizeOfListsNotEquals() {
        StringList expected = new MyArrayListString();
        expected.add(STRING1);
        expected.add(STRING5);
        expected.add(STRING3);
        expected.add(STRING4);
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
        expected = new String[]{STRING1, STRING2, STRING3, STRING4, STRING5};
        assertArrayEquals(expected, out.toArray());
    }
}