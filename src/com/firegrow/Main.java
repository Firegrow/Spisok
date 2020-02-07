package com.firegrow;

import java.util.Scanner;
import java.util.LinkedList;

public class Main {

    public static void find(LinkedList<Integer> spisok, int key) { //Обычный линейный поиск
        for (int i = 0; i < spisok.size(); i++) {
            if ((spisok.get(i) == key)) {
                System.out.println("Найдено, индекс: " + i);
                break;
            }
            if (i == spisok.size() - 1) {
                System.out.println("Не найдено");
            }
        }
    }

    public static boolean tryParseInt(String value) { //Проверка вводимых данных
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void sort(LinkedList<Integer> spisok, int low, int high) { //Используем quicksort
        if (spisok.size() == 0)
            return;
        if (low >= high)
            return;
        int middle = low + (high - low) / 2; //Выбираем опорный элемент
        int opora = spisok.get(middle);
        int l = low, h = high; //Разделяем на подмассивы
        while (l <= h) {
            while (spisok.get(l) < opora) {
                l++;
            }

            while (spisok.get(h) > opora) {
                h--;
            }

            if (l <= h) { //Меняем элементы
                int temp = spisok.get(l);
                spisok.set(l, spisok.get(h));
                spisok.set(h, temp);
                l++;
                h--;
            }
        }
        if (low < h)
            sort(spisok, low, h);
        if (high > l)
            sort(spisok, l, high);
    }

    public static void main(String[] args) {
        System.out.println("Привет, это пример моей программы, которая может работать с двусвязным списком из целых чисел: находить элементы, удалять их, добавлять и сортировать");
        System.out.println("Сейчас вы можете поочерёдно внести в список все желаемые числа, а когда закончите, введите stop:");
        Scanner vvod = new Scanner(System.in);
        LinkedList<Integer> spisok = new LinkedList<>();
        while (true) {
            String stroka = vvod.nextLine();
            if (stroka.equals("stop")) //Проверка на стоп-слово
                break;
            else {
                if (tryParseInt(stroka)) //Проверка является ли элемент string или int
                    spisok.add(Integer.parseInt(stroka));
                else
                    System.out.println("Ошибка ввода");
            }
        }
        while (true) {
            System.out.println("Что вы хотите сделать? (find; delete; add; sort; show; exit)");
            switch (vvod.nextLine()) {
                case "find":
                    System.out.println("Введите число для поиска:");
                    find(spisok, vvod.nextInt());
                    break;
                case "delete":
                    System.out.println("Введите индекс числа для удаления:");
                    spisok.remove(vvod.nextInt());
                    System.out.println("Удалено");
                    break;
                case "add":
                    System.out.println("Введите число для добавления:");
                    String dobav = vvod.nextLine();
                    if (tryParseInt(dobav))
                        spisok.add(Integer.parseInt(dobav));
                    else
                        System.out.println("Ошибка ввода");
                    break;
                case "sort":
                    sort(spisok, 0, spisok.size() - 1);
                    System.out.println(spisok);
                    break;
                case "show":
                    System.out.println(spisok);
                    break;
                case "exit":
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
