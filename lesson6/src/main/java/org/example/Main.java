package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import static org.example.Utils.FileReaderUtil.readBlackList;
import static org.example.Utils.FileReaderUtil.readComments;
import static org.example.Utils.FileWriterUtil.writeCommentsToFile;

public class Main {
    public static void main(String[] args) {
        String inCommentPath = "./src/main/resources/comments.txt";
        String inBlackListPath = "./src/main/resources/blacklist.txt";
        String outPath = "./src/main/resources/output.txt";
        if (args.length == 3) {
            inCommentPath = args[0];
            inBlackListPath = args[1];
            outPath = args[2];
        } else if (args.length != 0) {
            System.out.print("Правильная передача аргументов: <comments path> <blacklist path> <out comments path>");
            return;
        }

        ArrayList<String> comments;
        Set<String> blackList;
        try {
            comments = readComments(inCommentPath);
            blackList = readBlackList(inBlackListPath);
        } catch (IOException e) {
            System.out.print("Произошла ошибка при чтении файлов");
            return;
        }
        // По-хорошему тут бы статику, но в заданном интерфейсе ее нет.
        new SpamFiltering().filterComments(comments, blackList);
        try {
            writeCommentsToFile(outPath, comments);
        } catch (IOException e) {
            System.out.print("Возникла проблема при выводе результата");
            return;
        }
        System.out.print("Вывод выполнен по пути: " + outPath);
    }
}
