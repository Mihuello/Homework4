package Apples;

public class Apples {
    public static void NumberOfApples(String name, int apples) {
        String[] apple = {"яблок", "яблоко", "яблока"};
        int arbitraryVariable = apples % 10;
        String result = null;
        switch (arbitraryVariable) {
            case 1:
                result = apples == 11 ? apple[1] : apple[0];
                break;
            case 2:
            case 3:
            case 4:
                result = apples > 10 && apples < 20 ? apple[0] : apple[2];
                break;
            default:
                result = apple[0];
        }
        System.out.println(name + " хранит" + " " + apples + " " + result);
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            NumberOfApples("Михаил", i);
        }
    }
}
