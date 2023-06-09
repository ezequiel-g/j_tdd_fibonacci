package poc.tdd;

public class LoopImpl implements Fibonacci {

    @Override
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

}
