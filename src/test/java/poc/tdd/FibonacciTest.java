package poc.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

abstract class FibonacciTest {

    protected final Fibonacci fibonacci;

    FibonacciTest(final Fibonacci fibonacci) {
        this.fibonacci = fibonacci;
    }

    protected static Stream<Arguments> arguments() {
        return Stream.of(
                // TODO: 900 calculate highest values for fibonacci series
                Arguments.of(46, 1836311903),
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
                Arguments.of(4, 3),
                Arguments.of(3, 2),
                Arguments.of(2, 1),
                Arguments.of(1, 1)
                // TODO: 500 decide is we will include 0 as valid input output. https://es.wikipedia.org/wiki/Sucesi%C3%B3n_de_Fibonacci#Definici%C3%B3n_recurrente
        );
    }

    @ParameterizedTest
    @MethodSource("arguments")
    void get(final int input, final int expected) {
        assertEquals(expected, fibonacci.get(input));
    }


    @Disabled
    @Test
    void get_withNegativeInput_should() {
        // TODO: 200 check invalid negative values.
        assertEquals(-999, fibonacci.get(-1));
    }

}
