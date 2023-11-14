import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TestSpamFiltering {
    private SpamFiltering spamFiltering;

    /**
     * Инициализация нового объекта spamFiltering перед каждым тестом
     */
    @BeforeEach
    public void initializeSpamFiltering() {
        spamFiltering = new SpamFiltering();
    }

    /**
     * Тест фильтрации комментариев по черному списку
     */
    @Test
    public void testFilter() {
        // given:
        ArrayList<String> comments = new ArrayList<>(List.of("bla bln blin", "ale ale ale", "ps as"));
        Set<String> blackList = new HashSet<>();
        blackList.add("bla");
        blackList.add("as");
        ArrayList<String> expected = new ArrayList<>(List.of("ale ale ale"));

        // when:
        spamFiltering.filterComments(comments, blackList);

        // then:
        assertThat(comments)
                .isEqualTo(expected);
    }
}
