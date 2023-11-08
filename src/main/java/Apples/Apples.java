package Apples;

public class Apples {
    public static void NumberOfApples(String name, int apple) {
        String[] apples = {"яблок", "яблоко", "яблока"};
        int arbitraryVariable = apple % 10;
        String result;
        switch (arbitraryVariable) {
            case 1:
                result = apple == 11 ? apples[0] : apples[1];
                break;
            case 2:
            case 3:
            case 4:
                result = apple > 10 && apple < 20 ? apples[0] : apples[2];
                break;
            default:
                result = apples[0];
        }
        System.out.println(name + " хранит" + " " + apple + " " + result);
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            NumberOfApples("Михаил", i);
        }
    }
}
