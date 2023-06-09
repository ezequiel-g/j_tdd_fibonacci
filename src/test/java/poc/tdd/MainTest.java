package poc.tdd;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    private final Main main = new Main();

    @Test
    void test() {
        Main.main(new String[0]);
    }

    @Test
    void singleArgument() {
        assertEquals(List.of(2), main.get(3));
    }

    @Test
    void twoArguments() {
        assertEquals(List.of(2, 3, 5), main.get(3, 5));
    }

}