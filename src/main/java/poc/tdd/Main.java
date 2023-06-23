package poc.tdd;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {

    private static final Pattern DIGITS_PATTERN = Pattern.compile("^\\d+$");
    private static final String MSG_MISSING_ARGUMENTS = "missing arguments";
    private static final String MSG_SINGLE_ARGUMENT_SHOULD_BE_AN_INTEGER = "for single option it should be an integer";
    private static final String MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS = "for two arguments should be numbers";
    private final Fibonacci fibonacci = new LoopImpl();

    public static void main(final String[] args) {
        final Main main = new Main();
        main.validate(args);
        // TODO: process input using RecursiveImpl by default, printing values in console depending on values
        // TODO: process input choosing implementation
    }

    private boolean isNotANumber(final String arg) {
        return !DIGITS_PATTERN.matcher(arg).matches();
    }

    void validate(final String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException(MSG_MISSING_ARGUMENTS);
        }

        if (args.length == 1 && isNotANumber(args[0])) {
            throw new IllegalArgumentException(MSG_SINGLE_ARGUMENT_SHOULD_BE_AN_INTEGER);
        }

        if (args.length == 2) {
            if (isNotANumber(args[0])) {
                throw new IllegalArgumentException(MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS);
            }

            if (isNotANumber(args[1])) {
                throw new IllegalArgumentException(MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS);
            }
        }
    }

    public List<Integer> get(final int position) {
        return get(position, position);
    }

    public List<Integer> get(final int pos1, final int pos2) {
        return IntStream.range(pos1, pos2 + 1).map(fibonacci::get).boxed().toList();
    }

}
