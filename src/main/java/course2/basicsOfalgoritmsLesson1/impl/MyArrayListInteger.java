package course2.basicsOfalgoritmsLesson1.impl;

import course2.basicsOfalgoritmsLesson1.IntegerList;
import course2.basicsOfalgoritmsLesson1.exceptions.InvalidIndexException;
import course2.basicsOfalgoritmsLesson1.exceptions.NullItemException;
import course2.basicsOfalgoritmsLesson1.exceptions.NullListException;
import course2.basicsOfalgoritmsLesson1.exceptions.StringNotFoundException;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Arrays.asList;

public class MyArrayListInteger implements IntegerList {
    private Integer[] array;
    private int mainIndex;

    public MyArrayListInteger() {
        this.array = new Integer[0];
        this.mainIndex = 0;
    }

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public Integer add(Integer item) {
        validateItem(item);
        if (mainIndex < array.length) {
            array[mainIndex] = item;
        } else {
            Integer[] newArr = new Integer[array.length + 1];
            System.arraycopy(array, 0, newArr, 0, array.length);
            newArr[mainIndex] = item;
            array = newArr;
        }
        mainIndex++;
        return item;
    }

    // Добавление элемента
    // на определенную позицию списка.
    // Если выходит за пределы фактического
    // количества элементов или массива,
    // выбросить исключение.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public Integer add(int index, Integer item) {
        validateItem(item);
        validateIndex(index);
        Integer[] newArr = new Integer[array.length + 1];
        for (int i = 0; i < newArr.length; i++) {
            if (i < index) {
                newArr[i] = array[i];
            } else if (i == index) {
                newArr[i] = item;
            } else {
                newArr[i] = array[i - 1];
            }
        }
        array = newArr;
        mainIndex = newArr.length;
        return item;
    }

    // Установить элемент
    // на определенную позицию,
    // затерев существующий.
    // Выбросить исключение,
    // если индекс больше
    // фактического количества элементов
    // или выходит за пределы массива.
    @Override
    public Integer set(int index, Integer item) {
        validateItem(item);
        validateIndex(index);
        array[index] = item;
        return item;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int size = array.length;
        for (Integer s : array) {
            if (s.equals(item)) {
                size--;
            }
        }
        Integer[] newArr = new Integer[size];
        int newIndex = 0;
        for (Integer s : array) {
            if (!s.equals(item)) {
                newArr[newIndex] = s;
                newIndex++;
            }
        }
        if (!asList(array).contains(item)) {
            throw new StringNotFoundException("Такой строки нет в списке");
        }
        array = newArr;
        return item;
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public Integer remove(int index) {
        validateIndex(index);
        int size = array.length;
        for (int i = 0; i < array.length; i++) {
            if (i == index) {
                size--;
            }
        }
        Integer[] newArr = new Integer[size];

        int newIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != index) {
                newArr[newIndex] = array[i];
                newIndex++;
            }
        }
        array = newArr;
        return array[index];
    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    @Override
    public boolean contains(Integer item) {
        validateItem(item);
        sort(array);
        return binarySearch(item);
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.

    @Override
    public int indexOf(Integer item) {
        validateItem(item);
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(Integer item) {
        validateItem(item);
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // Получить элемент по индексу.
    // Вернуть элемент или исключение,
    // если выходит за рамки фактического
    // количества элементов.
    @Override
    public Integer get(int index) {
        validateIndex(index);
        return array[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList == null) {
            throw new NullListException("Переданный список не может быть null");
        }
        if (this.size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < this.size(); i++) {
            if (!this.get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Вернуть фактическое количество элементов.
    @Override
    public int size() {
        return array.length;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        array = new Integer[0];
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    @Override
    public Integer[] toArray() {
        Integer[] newArr = new Integer[array.length];
        System.arraycopy(array, 0, newArr, 0, array.length);
        return newArr;
    }

    // Создать отсортированный массив
    // из строк в списке
    // и вернуть его.
    @Override
    public Integer[] toSortedArray() {
        sort(array);
        Integer[] newArr = new Integer[array.length];
        System.arraycopy(array, 0, newArr, 0, array.length);
        return newArr;
    }

    //проверка переданного значения на null
    private void validateItem(Integer item) {
        if (item == null) {
            throw new NullItemException("Элемент не может быть равен null");
        }
    }

    // проверка индекса на корректность
    private void validateIndex(int index) {
        if (index >= array.length || index < 0) {
            throw new InvalidIndexException("Задан некорректный индекс");
        }
    }

    // выбрана сотрировка выбором, тк при проверке скорость выполнения она показала самый быстрый результат
    private void sort(Integer[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(Integer[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);

            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private int partition(Integer[] array, int begin, int end) {
        int pivot = array[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;

                swapElements(array, i, j);
            }
        }

        swapElements(array, i + 1, end);
        return i + 1;
    }

    private void swapElements(Integer[] array, int i1, int i2) {
        int temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }

    //приватный метод для бинарного поиска элемента
    private boolean binarySearch(Integer item) {
        int min = 0;
        int max = array.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (Objects.equals(item, array[mid])) {
                return true;
            }
            if (item < array[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    //приватный метод для расширения массива в 1,5 раза
    private void grow() {
        array = Arrays.copyOf(array, mainIndex + mainIndex / 2);
    }
}
