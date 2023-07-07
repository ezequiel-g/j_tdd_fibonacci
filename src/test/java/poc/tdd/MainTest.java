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

    private static final String MSG_MISSING_ARGUMENTS = "missing arguments";
    private static final String MSG_SINGLE_ARGUMENT_SHOULD_BE_AN_INTEGER = "for single option it should be an integer";
    private static final String MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS = "for two arguments should be numbers";
    private static final String MSG_ALL_REQUIRES_SECOND_OPTION_INTEGER = "when using all second argument should be an Integer";
    private static final String NEW_LINE_SEPARATOR = System.lineSeparator();

    private final Main main = new Main();

    // TODO: 800 can we make it static?
    private final PrintStream outDefault = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    // TODO: 100,  inline outCaptor, we cannot use it for validations.
    private final PrintStream outCaptor = new PrintStream(outputStreamCaptor);

    private static Stream<Arguments> argumentsInvalid() {
        return Stream.of(
                Arguments.of(new String[]{"all", "b"}, IllegalArgumentException.class, MSG_ALL_REQUIRES_SECOND_OPTION_INTEGER),
                Arguments.of(new String[]{"4", "b"}, IllegalArgumentException.class, MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS),
                Arguments.of(new String[]{"a", "b"}, IllegalArgumentException.class, MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS),
                Arguments.of(new String[]{"invalidOption"}, IllegalArgumentException.class, MSG_SINGLE_ARGUMENT_SHOULD_BE_AN_INTEGER),
                Arguments.of(null, IllegalArgumentException.class, MSG_MISSING_ARGUMENTS),
                Arguments.of(new String[0], IllegalArgumentException.class, MSG_MISSING_ARGUMENTS)
        );
    }

    @BeforeEach
    void setUp() {
        System.setOut(outCaptor);
    }

    @AfterEach
    void tearDown() {
        System.setOut(outDefault);
    }

    @ParameterizedTest
    @MethodSource("argumentsInvalid")
    void testInvalid(final String[] input, final Class<IllegalArgumentException> expectedType, final String expectedErrorMsg) {
        final IllegalArgumentException illegalArgumentException = assertThrows(expectedType, () -> main.validate(input));
        assertEquals(expectedErrorMsg, illegalArgumentException.getMessage());
    }

    @Test
    void show() {
        main.show("hello");
        assertEquals("hello" + NEW_LINE_SEPARATOR, outputStreamCaptor.toString());
    }

    @Test
    void mainTest() {
        Main.main(new String[]{"1"});
        assertEquals(List.of(1) + NEW_LINE_SEPARATOR, outputStreamCaptor.toString());
    }

    @Test
    void process() {
        assertEquals(List.of(1).toString(), main.process(new String[]{"1"}));
    }

    @Test
    void process2() {
        assertEquals(List.of(1, 1).toString(), main.process(new String[]{"1", "2"}));
    }

    @Test
    void process3() {
        assertEquals(List.of(1, 1, 2).toString(), main.process(new String[]{"all", "3"}));
    }

    @Test
    void mainTest2() {
        // TODO: 200 use System.err for errors output.
        Main.main(new String[]{"invalidOption"});
        assertEquals("ERROR: for single option it should be an integer" + NEW_LINE_SEPARATOR, outputStreamCaptor.toString());
    }

    @Test
    void singleArgument() {
        // TODO: 100 remove single get method from Main. process method is taking care of this feature using as input validated String[] args
        assertEquals(List.of(2), main.get(3));
    }

    @Test
    void twoArguments() {
        // TODO: 100 remove two integers get method from Main. process method is taking care of this feature using as input validated String[] args
        assertEquals(List.of(2, 3, 5), main.get(3, 5));
    }

}