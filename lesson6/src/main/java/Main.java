import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> comments;
        Set<String> blackList;
        try {
            comments = readComments("./src/main/resources/comments.txt");
            blackList = readBlackList("./src/main/resources/blackList.txt");
        } catch (IOException e) {
            System.out.print("Произошла ошибка при чтении файлов. Убедитесь, что такой файл существует.");
            return;
        }
        System.out.println(comments);
        new SpamFiltering().filterComments(comments, blackList);
        System.out.println(comments);
        try {
            writeCommentsToFile("./src/main/resources/result.txt", comments);
        } catch (IOException e) {
            System.out.print("Возникла проблема при выводе результата");
            return;
        }
    }

    public static ArrayList<String> readComments(String pathToFile) throws IOException {
        File file = new File(pathToFile);
        ArrayList<String> comments = new ArrayList<>();
        try (Scanner in = new Scanner(new FileReader(file))) {
            while (in.hasNext()) {
                comments.add(in.nextLine());
            }
        } catch (IOException e) {
            throw e;
        }
        return comments;
    }

    public static Set<String> readBlackList(String pathToFile) throws FileNotFoundException {
        File file = new File(pathToFile);
        HashSet<String> blackList = new HashSet<>();
        try (Scanner in = new Scanner(new FileReader(file))) {
            while (in.hasNext()) {
                blackList.add(in.nextLine());
            }
        } catch (IOException e) {
            throw e;
        }
        return blackList;
    }

    public static void writeCommentsToFile(String pathToFile, List<String> comments) throws IOException {
        File file = new File(pathToFile);
        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
            for (String comment :
                    comments) {
                out.write(comment + "\n");
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
