package calc;

public class SimpleCalculator {
    public static void main(String[] args) {
        SimpleCalculator calc = new SimpleCalculator();
        System.out.println("Сумма: " + calc.sum(4, 2));
        System.out.println("Разность: " + calc.diff(4, 2));
        System.out.println("Умножение: " + calc.mult(4, 2));
        System.out.println("Деление: " + calc.div(4, 2));

    }

    public int sum(int val1, int val2) throws ArithmeticException {
        long res = (long) val1 + val2;
        if (res >= Integer.MAX_VALUE || res <= Integer.MIN_VALUE) {
            throw new ArithmeticException("Переполнение на операции sum");
        }
        return val1 + val2;
    }

    public int diff(int val1, int val2) {
        long res = (long) val1 * val2;
        if (res >= Integer.MAX_VALUE || res <= Integer.MIN_VALUE) {
            throw new ArithmeticException("Переполнение на операции mult");
        }
        return val1 - val2;
    }

    public int mult(int val1, int val2) {
        long res = (long) val1 * val2;
        if (res >= Integer.MAX_VALUE || res <= Integer.MIN_VALUE) {
            throw new ArithmeticException("Переполнение на операции mult");
        }
        return val1 * val2;
    }

    public int div(int val1, int val2) {
        long res = (long) val1 / val2;
        if (res >= Integer.MAX_VALUE || res <= Integer.MIN_VALUE) {
            throw new ArithmeticException("Переполнение на операции div");
        }
        if (val2 == 0) {
            throw new ArithmeticException("Деление на ноль на операции div");
        }
        return val1 / val2;
    }

}
