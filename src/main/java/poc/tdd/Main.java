package poc.tdd;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {

    private static final Pattern DIGITS_PATTERN = Pattern.compile("^\\d+$");
    private static final String MSG_MISSING_ARGUMENTS = "missing arguments";
    private static final String MSG_SINGLE_ARGUMENT_SHOULD_BE_AN_INTEGER = "for single option it should be an integer";
    private static final String MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS = "for two arguments should be numbers";
    private static final String MSG_ALL_REQUIRES_SECOND_OPTION_INTEGER = "when using all second argument should be an Integer";
    private static final String ALL_OPTION = "all";
    private final Fibonacci fibonacci = new LoopImpl();

    public static void main(final String[] args) {
        final Main main = new Main();
        try {
            main.validate(args);
            final String result = main.process(args);
            main.show(result);
        } catch (final RuntimeException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        // TODO: 950 process input choosing implementation
    }

    private boolean isNotANumber(final String args) {
        return !DIGITS_PATTERN.matcher(args).matches();
    }

    void validate(final String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException(MSG_MISSING_ARGUMENTS);
        }

        if (args.length == 1 && isNotANumber(args[0])) {
            throw new IllegalArgumentException(MSG_SINGLE_ARGUMENT_SHOULD_BE_AN_INTEGER);
        }

        if (args.length == 2) {
            if (ALL_OPTION.equals(args[0])) {
                if (isNotANumber(args[1])) {
                    throw new IllegalArgumentException(MSG_ALL_REQUIRES_SECOND_OPTION_INTEGER);
                }
            } else {
                if (isNotANumber(args[0])) {
                    throw new IllegalArgumentException(MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS);
                }

                if (isNotANumber(args[1])) {
                    throw new IllegalArgumentException(MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS);
                }
            }

        }
    }

    List<Integer> get(final int position) {
        return get(position, position);
    }

    List<Integer> get(final int initialPosition, final int finalPosition) {
        return IntStream.range(initialPosition, finalPosition + 1).map(fibonacci::get).boxed().toList();
    }

    void show(final String output) {
        System.out.println(output);
    }

    String process(final String[] args) {
        int initialPosition = ALL_OPTION.equals(args[0]) ? 1 : Integer.parseInt(args[0]);
        int finalPosition = args.length == 1 ? initialPosition : Integer.parseInt(args[1]);
        return IntStream.range(initialPosition, finalPosition + 1).map(fibonacci::get).boxed().toList().toString();
    }

}
