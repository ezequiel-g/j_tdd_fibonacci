package poc.tdd;

import java.util.List;
import java.util.stream.IntStream;

public class Main {

    private final Fibonacci fibonacci = new RecursiveImpl();

    public static void main(final String[] args) {
        // TODO: process input using RecursiveImpl by default, printing values in console depending on values
        // TODO: process input choosing implementation
    }

    public List<Integer> get(final int position) {
        return get(position, position);
    }

    public List<Integer> get(final int pos1, final int pos2) {
        return IntStream.range(pos1, pos2 + 1).map(fibonacci::get).boxed().toList();
    }

}
