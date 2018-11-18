package pl.coderslab.Dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.coderslab.Entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class BookMemory implements Memorable
{
    private List<Book> list;
    private Long autoincrement;

    public BookMemory()
    {
        this.autoincrement = 0L;
        this.list = new ArrayList<>();
        this.list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        this.autoincrement++;
        this.list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        this.autoincrement++;
        this.list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
        this.autoincrement++;
    }

    @Override
    public List<Book> findAll()
    {
        return this.list;
    }

    @Override
    public Book getById(Long id)
    {
        try
        {
            return this.list.stream().filter(book -> book.getId().equals(id)).collect(Collectors.toList()).get(0);
        }
        catch (NullPointerException | IndexOutOfBoundsException e)
        {
            return null;
        }
    }

    @Override
    public void save(Object o)
    {
        Book book = (Book) o;
        if (book.getId() == null)
        {
            add(book);
        }
        else
        {
            update(book);
        }
    }

    @Override
    public void delete(Long id)
    {
        this.list.removeIf(b -> b.getId().equals(id));
    }

    private void add(Book book)
    {
        try
        {
            book.setId(++autoincrement);
            this.list.add(book);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void update(Book book)
    {
        try
        {
            int indexOfOldEl = this.list.indexOf(this.getById(book.getId()));
            this.list.set(indexOfOldEl, book);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
