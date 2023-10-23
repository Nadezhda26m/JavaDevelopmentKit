package compare;

/*
Напишите обобщенный метод compareArrays(), который принимает два массива и
возвращает true, если они одинаковые, и false в противном случае. Массивы могут быть
любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа.
 */

import java.util.Arrays;

public class Main {

    static <T> boolean compareArrays(T[] arr1, T[] arr2) {
        if (arr1 == null || arr2 == null)
            throw new NullPointerException("Неинициализированный массив");
        if (arr1.length != arr2.length)
            throw new RuntimeException("Массивы имеют разную длину");
        for (int i = 0; i < arr1.length; i++)
            if (!arr1[i].equals(arr2[i])) return false;
        return true;
    }

    static <T> boolean compareArrays2(T[] arr1, T[] arr2) {
        if (arr1 == null || arr2 == null)
            throw new NullPointerException("Неинициализированный массив");
        if (arr1.getClass() != arr2.getClass())
            throw new RuntimeException("Разные типы данных");
        if (arr1.length != arr2.length)
            throw new RuntimeException("Массивы имеют разную длину");
        for (int i = 0; i < arr1.length; i++)
            if (!arr1[i].equals(arr2[i])) return false;
        return true;
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3, 4, 5};
        Integer[] arr2 = {1, 2, 3, 4, 5};
        Integer[] arr3 = arr1;
        Integer[] arr4 = {1, 2, 3, 4, 5, 6};
        Integer[] arr5 = {1, 2, 7, 4, 5};
        Integer[] arr6 = null;
        String[] str1 = {"aa", "bb"};
        String[] str2 = {"ab", "cd"};
        String[] str3 = {"ab", "cd", "ef", "gh", "k"};
        Number[] arr7 = {1, 5.3, 18f};
        Number[] arr8 = {1, 5.3f, 18f};

        System.out.println(Arrays.toString(arr1)); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(arr2)); // [1, 2, 3, 4, 5]
        System.out.println(compareArrays(arr1, arr2) + "\n"); // true

        System.out.println(Arrays.toString(arr1)); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(arr3)); // [1, 2, 3, 4, 5]
        System.out.println(compareArrays(arr1, arr3) + "\n"); // true

        System.out.println(Arrays.toString(arr1)); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(arr4)); // [1, 2, 3, 4, 5, 6]
        try {
            System.out.println(compareArrays(arr1, arr4) + "\n");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + "\n"); // Массивы имеют разную длину
        }

        System.out.println(Arrays.toString(arr1)); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(arr5)); // [1, 2, 7, 4, 5]
        System.out.println(compareArrays(arr1, arr5) + "\n"); // false

        System.out.println(Arrays.toString(arr1)); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(arr6)); // null
        try {
            System.out.println(compareArrays(arr1, arr6) + "\n");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + "\n"); // Неинициализированный массив
        }

        System.out.println(Arrays.toString(arr1)); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(str3)); // ["ab", "cd", "ef", "gh", "k"]
        System.out.println(compareArrays(arr1, str3) + "\n"); // false ???

        System.out.println(Arrays.toString(arr1)); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.toString(str3)); // ["ab", "cd", "ef", "gh", "k"]
        try {
            System.out.println(compareArrays2(arr1, str3) + "\n"); //
        } catch (RuntimeException e) {
            System.out.println(e.getMessage() + "\n"); // Разные типы данных
        }

        System.out.println(Arrays.toString(str1)); // ["aa", "bb"]
        System.out.println(Arrays.toString(str2)); // ["ab", "cd"]
        System.out.println(compareArrays(str1, str2) + "\n"); // false

        System.out.println(Arrays.toString(arr7)); // [1, 5.3, 18f]
        System.out.println(Arrays.toString(arr8)); // [1, 5.3f, 18f]
        System.out.println(compareArrays2(arr7, arr8) + "\n"); // false
    }

}
