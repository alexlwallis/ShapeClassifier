import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class ShapeWhiteBoxTests {

    @Test
    void testValidInputFormat() {
        String input = "Rectangle,Large,Yes,10,20,10,20";
        String result = evaluateGuess(input);
        assertNotNull(result, "Result should not be null for valid input.");
    }

    @Test
    void testInvalidInputFormatMissingParams() {
        String input = "Rectangle,Large,Yes"; // Missing Params
        Exception exception = assertThrows(IllegalArgumentException.class, () -> evaluateGuess(input));
        assertEquals("Invalid input format", exception.getMessage());
    }

    @Test
    void testInvalidInputNonNumericParams() {
        String input = "Rectangle,Large,Yes,10,abc,20,30"; // Non-numeric parameter
        Exception exception = assertThrows(NumberFormatException.class, () -> evaluateGuess(input));
        assertEquals("Invalid number format", exception.getMessage());
    }

    @Test
    void testEmptyInput() {
        String input = "";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> evaluateGuess(input));
        assertEquals("Input cannot be empty", exception.getMessage());
    }

    @Test
    void testLineShapeBoundary() {
        String input = "Line,Small,Yes,0"; // Perimeter = 0
        String result = evaluateGuess(input);
        assertEquals("Yes", result, "Line with 0 perimeter should return Yes if guessed correctly.");
    }

    @Test
    void testCircleBoundary() {
        String input = "Circle,Large,No,50"; // Assume Params is the radius
        String result = evaluateGuess(input);
        assertEquals("Yes", result, "Circle with radius 50 should return Yes for correct guesses.");
    }

    @Test
    void testEquilateralTriangle() {
        String input = "Equilateral,Large,Yes,100,100,100"; // Perimeter = 300
        String result = evaluateGuess(input);
        assertEquals("Yes", result, "Equilateral triangle with perimeter 300 should return Yes for correct guesses.");
    }

    @Test
    void testSquareSmallBoundary() {
        String input = "Square,Small,Yes,5,5,5,5"; // Perimeter = 20
        String result = evaluateGuess(input);
        assertEquals("Yes", result, "Square with perimeter 20 should return Yes for correct guesses.");
    }

    @Test
    void testIncorrectShapeGuess() {
        String input = "Circle,Large,Yes,10,10,10,10"; // Params do not match Circle
        String result = evaluateGuess(input);
        assertEquals("Shape guess incorrect. Did you mean: Square?", result, "Incorrect shape guess should return suggestion.");
    }

    @Test
    void testIncorrectSizeGuess() {
        String input = "Rectangle,Small,Yes,50,50,50,50"; // Perimeter = 200 (Large)
        String result = evaluateGuess(input);
        assertEquals("Size guess incorrect.", result, "Incorrect size guess should return size error.");
    }

    @Test
    void testIncorrectEvenOddGuess() {
        String input = "Equilateral,Large,Yes,33,33,33"; // Perimeter = 99 (Odd)
        String result = evaluateGuess(input);
        assertEquals("Even/Odd guess incorrect.", result, "Incorrect even/odd guess should return parity error.");
    }

    @Test
    void testMultipleIncorrectGuesses() {
        String input = "Circle,Small,Yes,10,10,10,10"; // Incorrect shape, size, and parity
        Exception exception = assertThrows(RuntimeException.class, () -> evaluateGuess(input));
        assertEquals("Too many incorrect guesses. Exiting.", exception.getMessage());
    }
}