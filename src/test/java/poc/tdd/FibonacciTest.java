package poc.tdd;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FibonacciTest {

    private final Fibonacci fibonacci = new Fibonacci();

    private static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(25, 75025),
                Arguments.of(17, 1597),
                Arguments.of(16, 987),
                Arguments.of(15, 610),
                Arguments.of(14, 377),
                Arguments.of(13, 233),
                Arguments.of(12, 144),
                Arguments.of(11, 89),
                Arguments.of(10, 55),
                Arguments.of(9, 34),
                Arguments.of(8, 21),
                Arguments.of(7, 13),
                Arguments.of(6, 8),
                Arguments.of(5, 5),
                Arguments.of(3, 2),
                Arguments.of(2, 1),
                Arguments.of(1, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void testParams(final int input, final int expected) {
        assertEquals(expected, fibonacci.get(input));
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void testParamsRecursive(final int input, final int expected) {
        assertEquals(expected, fibonacci.getRecursive(input));
    }

}
