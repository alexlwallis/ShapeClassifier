import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShapeRandomTests {

    @Test
    void testValidCircleGuess() {
        // Input: Circle with radius 50 (perimeter ~314), guessed correctly
        String input = "Circle,Large,Yes,50";
        
        // Expected Output: All guesses are correct
        String expectedOutput = "Yes";

        String result = evaluateGuess(input);
        assertEquals(expectedOutput, result, "Valid guesses for a Circle with radius 50 should return 'Yes'.");
    }
}