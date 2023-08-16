package course2.basicsOfalgoritmsLesson1;

import course2.basicsOfalgoritmsLesson1.exceptions.InvalidIndexException;
import course2.basicsOfalgoritmsLesson1.exceptions.NullItemException;
import course2.basicsOfalgoritmsLesson1.exceptions.NullListException;
import course2.basicsOfalgoritmsLesson1.exceptions.StringNotFoundException;

import java.util.Arrays;

public class MyArrayList implements StringList {
    private String[] arr;
    private int mainIndex;

    public MyArrayList() {
        this.arr = new String[0];
        this.mainIndex = 0;
    }

    // Добавление элемента.
    // Вернуть добавленный элемент
    // в качестве результата выполнения.
    @Override
    public String add(String item) {
        validateItem(item);
        if (mainIndex < arr.length) {
            arr[mainIndex] = item;
        } else {
            String[] newArr = new String[arr.length + 1];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            newArr[mainIndex] = item;
            arr = newArr;
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
    public String add(int index, String item) {
        validateItem(item);
        validateIndex(index);
            String[] newArr = new String[arr.length + 1];
            for (int i = 0; i < newArr.length; i++) {
                if (i < index) {
                    newArr[i] = arr[i];
                } else if (i == index) {
                    newArr[i] = item;
                } else {
                    newArr[i] = arr[i - 1];
                }
            }
            arr = newArr;
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
    public String set(int index, String item) {
        validateItem(item);
        validateIndex(index);
            arr[index] = item;
            return item;
    }

    // Удаление элемента.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(String item) {
        validateItem(item);
        int size = arr.length;
        for (String s : arr) {
            if (s.equals(item)) {
                size--;
            }
        }
        String[] newArr = new String[size];
        int newIndex = 0;
        for (String s : arr) {
            if (!s.equals(item)) {
                newArr[newIndex] = s;
                newIndex++;
            }
        }
        if (!Arrays.asList(arr).contains(item)) {
            throw new StringNotFoundException("Такой строки нет в списке");
        }
        arr = newArr;
        return item;
    }

    // Удаление элемента по индексу.
    // Вернуть удаленный элемент
    // или исключение, если подобный
    // элемент отсутствует в списке.
    @Override
    public String remove(int index) {
        validateIndex(index);
        int size = arr.length;
        for (int i = 0; i < arr.length; i++) {
            if (i == index) {
                size--;
            }
        }
        String[] newArr = new String[size];

        int newIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                newArr[newIndex] = arr[i];
                newIndex++;
            }
        }
        arr = newArr;
        return arr[index];
    }

    // Проверка на существование элемента.
    // Вернуть true/false;
    @Override
    public boolean contains(String item) {
        validateItem(item);
        for (String s : arr) {
            if (s.equals(item)) {
                return true;
            }
        }
        return false;
    }

    // Поиск элемента.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int indexOf(String item) {
        validateItem(item);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // Поиск элемента с конца.
    // Вернуть индекс элемента
    // или -1 в случае отсутствия.
    @Override
    public int lastIndexOf(String item) {
        validateItem(item);
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i].equals(item)) {
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
    public String get(int index) {
        validateIndex(index);
            return arr[index];
    }

    // Сравнить текущий список с другим.
    // Вернуть true/false или исключение,
    // если передан null.
    @Override
    public boolean equals(StringList otherList) {
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
        return arr.length;
    }

    // Вернуть true,
    // если элементов в списке нет,
    // иначе false.
    @Override
    public boolean isEmpty() {
        return arr.length == 0;
    }

    // Удалить все элементы из списка.
    @Override
    public void clear() {
        arr = new String[0];
    }

    // Создать новый массив
    // из строк в списке
    // и вернуть его.
    @Override
    public String[] toArray() {
        String[] newArr = new String[arr.length];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        return newArr;
    }

    private void validateItem(String item) {
        if (item == null) {
            throw new NullItemException("Элемент не может быть равен null");
        }
    }

    private void validateIndex(int index) {
        if (index >= arr.length || index < 0) {
            throw new InvalidIndexException("Задан некорректный индекс");
        }
    }
}
