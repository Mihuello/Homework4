package HardcoreGame;

import java.util.*;

public class Game {
    static void printGame() {
        System.out.println("Привет! Предлагаю сыграть в игру, правила у нее следующие:дана последовательность целых чисел. Числа могут быть от 0 до 9\n" +
                "Вы можете вычеркнуть (убрать из последовательности) два рядом стоящих числа, если они одинаковы или в сумме дают 9.\n" +
                "За каждое вычеркивание Вы получаете 1 очко\n" +
                "Игра заканчивается, когда не останется возможных комбинаций\n");
    }

    static void Game(List<Integer> integers) {
        boolean ready;
        printGame();
        ready = readyToGame();
        if (ready) {
            System.out.println("Давайте тогда начинать!");
            timeOfGame(integers);
        } else {
            System.out.println("Хорошо, удачи в следующий раз!");
        }
    }

    static boolean checkArrayIsEmpty(List<Integer> arrays) {
        return arrays.isEmpty();
    }

    static boolean checkingTheArrayForAdjacentNumbers(List<Integer> arrayForAdjacentNumber) {
        boolean adjacentNumbers = false;
        for (int a = 1; a < arrayForAdjacentNumber.size(); a++) {
            for (int i = a - 1; i < a; i++) {
                if (arrayForAdjacentNumber.get(a).equals(arrayForAdjacentNumber.get(i))) {
                    adjacentNumbers = true;
                }
            }
        }
        return adjacentNumbers;
    }

    static boolean checkingAnArrayForTheSumOfNine(List<Integer> array) {
        boolean c = false;
        for (int a = 1; a < array.size(); a++) {
            for (int i = a - 1; i < a; i++) {
                if (array.get(a) + array.get(i) == 9) {
                    c = true;
                    break;
                }
            }
        }
        return c;
    }

    static void timeOfGame(List<Integer> array) {
        int score = 0;
        for (; ; ) {
            if (checkArrayIsEmpty(array) || (!checkingTheArrayForAdjacentNumbers(array) && !checkingAnArrayForTheSumOfNine(array))) {
                endOfTheGame(score);
                reasonForEndingTheGame(array);
                break;
            }
            showPlayingField(array);
            int number1 = numberFromUser();
            int number2 = numberFromUser();
            score += checkArray(array, number1, number2);
        }
    }

    static int numberFromUser() {
        Scanner scanner = new Scanner(System.in);
        int number;
        System.out.println("Введите целое число: ");
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Введите корректное целое число: ");
        }
        number = scanner.nextInt();
        return number;
    }

    static int checkArray(List<Integer> array, int number1, int number2) {
        int result = 0;
        if (array.contains(number1) && array.contains(number2)) {
            if (number1 == number2) {
                result = checkingForTheSameNumbersFromThePlayer(array, number1, number2);
            } else result = checkingTheNumbersFromThePlayerThatGiveTheSumNine(array, number1, number2);
        } else System.out.println("Указанных чисел или числа нет в массиве!");
        return result;
    }

    static int checkingForTheSameNumbersFromThePlayer(List<Integer> array, int number1, int number2) {
        int result = 0;
        for (int a = 1; a < array.size(); a++) {
            for (int c = a - 1; c < a; c++) {
                if ((array.get(a) == number1 && array.get(c) == number2) ||
                        (array.get(c) == number1) && array.get(a) == number2) {
                    if (array.get(a).equals(array.get(c))) {
                        result++;
                        array.remove(c);
                        array.remove(c);
                        System.out.println("Так держать! Вы нашли 2 одинаковых числа, стоящие рядом!");
                        a--;
                        break;
                    }
                }
            }
        }
        return result;
    }

    static int checkingTheNumbersFromThePlayerThatGiveTheSumNine(List<Integer> array, int number1, int number2) {
        int result = 0;
        for (int a = 1; a < array.size(); a++) {
            for (int i = a - 1; i < a; i++) {
                if ((array.get(a) == number1 && array.get(i) == number2) ||
                        (array.get(i) == number1) && array.get(a) == number2) {
                    if (array.get(i) + array.get(a) == 9) {
                        result++;
                        array.remove(i);
                        array.remove(i);
                        System.out.println("Так держать! Вы нашли числа дающие сумму 9!");
                        break;
                    }
                }
            }
        }
        return result;
    }

    static boolean readyToGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Готовы ли вы сыграть? да или нет?");
        scanner.hasNextLine();
        boolean b = scanner.nextLine().equalsIgnoreCase("да");
        return b;
    }

    static List createArray() {
        Random random = new Random();
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(random.nextInt(10));
        }
        integers = checkArray(integers);
        return integers;
    }

    static void showPlayingField(List<Integer> array) {
        System.out.println("На текущий момент даны числа: " + array);
    }

    static List<Integer> checkArray(List<Integer> array) {
        for (; ; ) {
            if (checkingTheArrayForAdjacentNumbers(array) || checkingAnArrayForTheSumOfNine(array)) {
                break;
            } else {
                array = createArray();
            }
        }
        return array;
    }


    static void endOfTheGame(int score) {
        System.out.println("Игра окончена! Ваш счет: " + score);
    }

    static void reasonForEndingTheGame(List<Integer> array) {
        String reason = null;
        if (checkArrayIsEmpty(array)) {
            reason = "Больше чисел не осталось";
        } else if (!checkingAnArrayForTheSumOfNine(array)) {
            reason = "Данные числа не дают сумму 9 или одинаковых чисел рядом стоящих  не осталось ";
        }
        System.out.println("Причина окончания игры: " + reason);
        System.out.println("Игровое поле после окончания игры: " + array);
    }


    public static void main(String[] args) {
        List<Integer> integers = createArray();
        Game(integers);
    }
}


