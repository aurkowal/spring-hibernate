package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Book;
import pl.coderslab.dao.BookDao;

@Controller
public class BookController {
    private final BookDao bookDao;

    public BookController(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/book/add")
    @ResponseBody
    public String add() {
        Book book = new Book();
        book.setTitle("Harry Potter");
        book.setDescription("Czara ognia");
        book.setRating(10);
        bookDao.save(book);
        return "Dodano ksiazke o id: " + book.getId();
    }
    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable("id") long id) {
        Book book = bookDao.find(id);
        return book.toString();
    }

    @RequestMapping("/book/update/{id}/{title}/{description}")
    @ResponseBody
    public String updateBook(@PathVariable("id") long id, @PathVariable("title") String title, @PathVariable("description") String desc) {
        Book book = bookDao.find(id);
        book.setTitle(title);
        book.setDescription(desc);
        bookDao.update(book);
        return book.toString();

    }

    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable("id") long id) {
        Book book = bookDao.find(id);
        bookDao.delete(book);
        return "usunieto ksiazke";
    }


}