import com.company.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestPalindrome {
    @Test
    @DisplayName("Checking palindrome text")
    void testPalindromeText() {
        String text = "А Роза... УпалА на лАпУ Азора?!";
        Assertions.assertTrue(Main.isPalindrome(text));
    }

    @Test
    @DisplayName("Checking not palindrome text")
    void testNotPalindromeText() {
        String text = "А Роза... на лАпУ Азора?!";
        Assertions.assertFalse(Main.isPalindrome(text));
    }
}
