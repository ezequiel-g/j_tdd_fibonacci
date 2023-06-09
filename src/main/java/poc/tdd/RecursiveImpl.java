package poc.tdd;

public class RecursiveImpl implements Fibonacci {

    @Override
    public int get(final int position) {
        if (position <= START_LOOP) {
            return 1;
        }

        return get(position - 1) + get(position - 2);
    }

}
