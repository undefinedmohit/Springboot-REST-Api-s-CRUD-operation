package com.devtool.book.devtoolbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devtool.book.devtoolbook.dao.BookRepository;
import com.devtool.book.devtoolbook.entities.Book;

@Component
public class BookServices {

    @Autowired
    private BookRepository bookRepository;

    // private static List<Book> list= new ArrayList<>();

    // static{
    // list.add(new Book(12, "C Language", "XYZ"));
    // list.add(new Book(15, "Java Language", "ABC"));
    // list.add(new Book(17, "Python Language", "LMN"));
    // }

    // get all books

    public List<Book> getBooks() {
        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }

    // get a book by id

    public Book getBookById(int id) {
        Book book = null;
        try {
            book=bookRepository.findById(id);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return book;
    }

    // add a book
    public Book addBook(Book b) {
        bookRepository.save(b);
        return b;
    }

    // deleting a book

    public void deleteBook(int bid) {

        // list = list.stream().filter(book -> book.getId() != bid).collect(Collectors.toList());
        bookRepository.deleteById(bid);
    }

    // updating a book details

    public void updatBook(Book book, int bookId) {
        // list = list.stream().map(b -> {
        //     if (b.getId() == bookId) {
        //         b.setName(book.getName());
        //         b.setAuthor(book.getAuthor());
        //     }
        //     return b;
        // }).collect(Collectors.toList());

        
        book.setId(bookId);
        book.setName(book.getName());
        book.setAuthor(book.getAuthor());
        bookRepository.save(book);
    }

}
