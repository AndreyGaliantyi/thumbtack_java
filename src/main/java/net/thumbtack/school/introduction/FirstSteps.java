package net.thumbtack.school.introduction;

public class FirstSteps {

    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        return x / y;
    }

    public int mod(int x, int y) {
        return x % y;
    }

    public boolean isEqual(int x, int y) {
        return x == y;
    }

    public boolean isGreater(int x, int y) {
        return x > y;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y) {
        return xLeft <= x && x <= xRight && yTop <= y && y <= yBottom;
    }

    public int sum(int[] array) {
        int res = 0;
        if (array.length != 0) {
            for (int i : array) res += i;
        }
        return res;
    }

    public int mul(int[] array) {
        int length = array.length;
        if (length != 0) {
            int res = array[0];
            for (int i = 1; i < length; i++) {
                res *= array[i];
            }
            return res;
        } else return 0;
    }

    public int min(int[] array) {
        int m = Integer.MAX_VALUE;
        for (int element : array) {
            if (element < m) m = element;
        }
        return m;
    }

    public int max(int[] array) {
        int m = Integer.MIN_VALUE;
        for (int element : array) {
            if (element > m) m = element;
        }
        return m;
    }

    public double average(int[] array) {
        double res = 0;
        int length = array.length;
        if (length != 0) {
            for (int i : array) res += i;
            res = res / length;
        }
        return res;
    }

    public boolean isSortedDescendant(int[] array) {
        int length = array.length;
        if (length > 1)
            for (int i = 1; i < length; i++) {
                if (array[i - 1] <= array[i]) return false;
            }
        return true;
    }

    public void cube(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * array[i] * array[i];
        }
    }

    public boolean find(int[] array, int value) {
        for (int i : array) {
            if (isEqual(i, value)) return true;
        }
        return false;
    }

    public void reverse(int[] array) {
        int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            int element = array[i];
            array[i] = array[length - i - 1];
            array[length - i - 1] = element;
        }
    }

    public boolean isPalindrome(int[] array) {
        int length = array.length;
        if (length > 1) {
            for (int i = 0; i < length / 2; i++)
                if (array[i] != array[length - i - 1]) return false;
        }
        return true;
    }

    public int sum(int[][] matrix) {
        int res = 0;
        for (int[] line : matrix) {
            for (int element : line) {
                res += element;
            }
        }
        return res;
    }

    public int max(int[][] matrix) {
        int m = Integer.MIN_VALUE;
        for (int[] line : matrix) {
            for (int element : line) {
                if (element > m) m = element;
            }
        }
        return m;
    }

    public int diagonalMax(int[][] matrix) {
        int m = Integer.MIN_VALUE;
        int length = matrix.length;
        if (length != 0) {
            for (int i = 0; i < length; i++) {
                if (matrix[i][i] > m) m = matrix[i][i];
            }
        }
        return m;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        int length = matrix.length;
        if (length != 0) {
            for (int[] line : matrix) {
                if (!isSortedDescendant(line)) return false;
            }
        }
        return true;
    }

}
