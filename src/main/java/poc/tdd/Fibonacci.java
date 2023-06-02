package poc.tdd;

public class Fibonacci {

    private static final int START_LOOP = 2;

    public int get(final int position) {

        int first = 0;
        int second = 1;
        int result = first + second;

        for (int i = START_LOOP; i <= position; i++) {
            result = first + second;

            first = second;
            second = result;
        }

        return result;
    }

    public int getRecursive(final int position) {
        if (position <= START_LOOP) {
            return 1;
        }

        return getRecursive( position - 1) + getRecursive(position - 2);
    }

}
