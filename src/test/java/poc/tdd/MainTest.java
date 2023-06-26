package poc.tdd;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {

    private static final String MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS = "for two arguments should be numbers";
    private static final String MSG_SINGLE_ARGUMENT_SHOULD_BE_AN_INTEGER = "for single option it should be an integer";
    private static final String MSG_MISSING_ARGUMENTS = "missing arguments";

    private final Main main = new Main();

    // TODO: can we make it static?
    private final PrintStream outDefault = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static Stream<Arguments> argumentsInvalid() {
        return Stream.of(
                // TODO: add "all" case
                Arguments.of(new String[]{"4", "b"}, IllegalArgumentException.class, MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS),
                Arguments.of(new String[]{"a", "b"}, IllegalArgumentException.class, MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS),
                Arguments.of(new String[]{"invalidOption"}, IllegalArgumentException.class, MSG_SINGLE_ARGUMENT_SHOULD_BE_AN_INTEGER),
                Arguments.of(null, IllegalArgumentException.class, MSG_MISSING_ARGUMENTS),
                Arguments.of(new String[0], IllegalArgumentException.class, MSG_MISSING_ARGUMENTS)
        );
    }

    @BeforeEach
    void setUp() {
        // TODO: move the new printer to a field and inline the ByteArray
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(outDefault);
    }

    @ParameterizedTest
    @MethodSource("argumentsInvalid")
    void testInvalid(final String[] input, final Class<IllegalArgumentException> expectedType, final String expectedErrorMsg) {
        final IllegalArgumentException illegalArgumentException = assertThrows(expectedType, () -> Main.main(input));
        assertEquals(expectedErrorMsg, illegalArgumentException.getMessage());
    }

    @Test
    void show() {
        main.show("hello");
        assertEquals("hello\r\n", outputStreamCaptor.toString());
    }

    @Test
    void mainTest() {
        Main.main(new String[]{"1"});
        // TODO: replace newLine character with System defined lineSeparator
        assertEquals(List.of(1) + "\r\n", outputStreamCaptor.toString());
    }

    @Test
    void process() {
        assertEquals(List.of(1).toString(), main.process(new String[]{"1"}));
    }

    @Test
    void singleArgument() {
        // TODO: remove single get method from Main.
        assertEquals(List.of(2), main.get(3));
    }

    @Test
    void twoArguments() {
        assertEquals(List.of(2, 3, 5), main.get(3, 5));
    }

}