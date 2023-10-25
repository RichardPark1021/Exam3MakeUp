import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookList {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader("src/Nobel.csv"))) {
            String line;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch(IOException e) {
            e.printStackTrace();
        }

        try(BufferedReader reader = new BufferedReader(new FileReader("src/Nobel.csv"))) {
            String line;

            while((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 3) {
                    String title = parts[0];
                    String author = parts[1];
                    int yearPublished = Integer.parseInt(parts[2]);
                    Book book = new Book(title, author, yearPublished);
                    books.add(book);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        Collections.sort(books, Comparator.comparingInt(Book::getYearPublished));

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/output.txt"))) {
            for (Book book : books) {
                writer.write(book.getTitle() + "," + book.getAuthor() + "," + book.getYearPublished());
                writer.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
