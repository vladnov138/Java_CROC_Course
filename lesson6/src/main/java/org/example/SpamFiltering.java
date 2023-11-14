package org.example;

import java.util.List;
import java.util.Set;

/**
 * Фильтрует комментарии по заданному фильтру
 *
 * @author Vladislav Novikov
 */
public class SpamFiltering implements BlackListFilter {

    @Override
    public void filterComments(List<String> comments, Set<String> blackList) {
        comments.removeIf(comment -> {
            for (String word :
                    comment.split("\\s")) {
                if (blackList.contains(word)) {
                    return true;
                }
            }
            return false;
        });
    }
}
