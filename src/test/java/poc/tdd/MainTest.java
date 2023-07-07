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

    private static final String ALL_OPTION = "all";
    private static final String MSG_MISSING_ARGUMENTS = "missing arguments";
    private static final String MSG_SINGLE_ARGUMENT_SHOULD_BE_AN_INTEGER = "for single option it should be an integer";
    private static final String MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS = "for two arguments should be numbers";
    private static final String MSG_ALL_REQUIRES_SECOND_OPTION_INTEGER = "when using all second argument should be an Integer";
    private static final String NEW_LINE_SEPARATOR = System.lineSeparator();

    // TODO: 100 can we make it static?
    private final PrintStream outDefault = System.out;
    private final PrintStream errDefault = System.err;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errStreamCaptor = new ByteArrayOutputStream();

    /**
     * Subject Under Test (sut)
     */
    private final Main main = new Main();

    private static Stream<Arguments> validateInvalidArguments() {
        return Stream.of(
                Arguments.of(new String[]{ALL_OPTION, "b"}, IllegalArgumentException.class, MSG_ALL_REQUIRES_SECOND_OPTION_INTEGER),
                Arguments.of(new String[]{"4", "b"}, IllegalArgumentException.class, MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS),
                Arguments.of(new String[]{"a", "b"}, IllegalArgumentException.class, MSG_BOTH_ARGUMENTS_SHOULD_BE_INTEGERS),
                Arguments.of(new String[]{" Any Invalid Option "}, IllegalArgumentException.class, MSG_SINGLE_ARGUMENT_SHOULD_BE_AN_INTEGER),
                Arguments.of(null, IllegalArgumentException.class, MSG_MISSING_ARGUMENTS),
                Arguments.of(new String[0], IllegalArgumentException.class, MSG_MISSING_ARGUMENTS)
        );
    }

    private static Stream<Arguments> processValidArguments() {
        return Stream.of(
                Arguments.of(new String[]{ALL_OPTION, "3"}, List.of(1, 1, 2).toString()),
                Arguments.of(new String[]{"1", "2"}, List.of(1, 1).toString()),
                Arguments.of(new String[]{"1"}, List.of(1).toString())
        );
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(errStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(outDefault);
        System.setErr(errDefault);
    }

    @ParameterizedTest
    @MethodSource("validateInvalidArguments")
    void validate(final String[] input, final Class<IllegalArgumentException> expectedType, final String expectedErrorMsg) {
        final IllegalArgumentException illegalArgumentException = assertThrows(expectedType, () -> main.validate(input));
        assertEquals(expectedErrorMsg, illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @MethodSource("processValidArguments")
    void process(final String[] input, final String expected) {
        assertEquals(expected, main.process(input));
    }

    @Test
    void out() {
        final String msg = " Any Message To Out ";
        main.out(msg);
        assertEquals(formattedOutput(msg), outputStreamCaptor.toString());
    }

    @Test
    void err() {
        final String msg = " Any Error To Err ";
        main.err(msg);
        assertEquals(formattedOutput(msg), errStreamCaptor.toString());
    }

    private String formattedOutput(final String msg) {
        return msg + NEW_LINE_SEPARATOR;
    }

    @Test
    void main_withValidInput_shouldPrintToConsole() {
        final String[] input = {"1"};
        final String expectedOut = formattedOutput(List.of(1).toString());
        final String expectedErr = "";
        mainTest(input, expectedOut, expectedErr);
    }

    // TODO: 150 convert to parametrized tests.
    private void mainTest(final String[] input, final String expectedOut, final String expectedErr) {
        Main.main(input);
        assertEquals(expectedOut, outputStreamCaptor.toString());
        assertEquals(expectedErr, errStreamCaptor.toString());
    }

    @Test()
    void main_withInvalidInput_shouldPrintToErrorConsole() {
        final String[] input = {"invalidOption"};
        final String expectedOut = "";
        final String expectedErr = formattedOutput("ERROR: for single option it should be an integer");
        mainTest(input, expectedOut, expectedErr);
    }

}