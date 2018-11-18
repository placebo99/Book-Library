package pl.coderslab.Controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.Dao.Memorable;
import pl.coderslab.Entity.Book;

import java.util.List;

@RestController // thanks to this produces=json is not required
public class BookController
{
    private final Memorable<Book> dao;

    //@Autowired
    public BookController(Memorable<Book> dao)
    {
        this.dao = dao;
    }

    @GetMapping("/books")
    public List<Book> getAll()
    {
        return dao.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getById(@PathVariable Long id)
    {
        return dao.getById(id);
    }

    @DeleteMapping("/books/{id}")
    public boolean deleteBook(@PathVariable Long id)
    {
        dao.delete(id);
        return true;
    }

    @PostMapping("/books")
    public Book add(@RequestBody Book book)
    {
        dao.save(book);
        return book;
    }

    @PutMapping("/books/{id}")
    public Book update(@RequestBody Book book)
    {
        dao.save(book);
        return book;
    }
}
