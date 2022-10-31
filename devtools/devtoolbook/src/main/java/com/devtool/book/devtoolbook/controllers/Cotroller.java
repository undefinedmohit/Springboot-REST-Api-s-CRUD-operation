package com.devtool.book.devtoolbook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devtool.book.devtoolbook.entities.Book;
import com.devtool.book.devtoolbook.services.BookServices;

@RestController
public class Cotroller {

    @Autowired
    private BookServices bookServices;

    // get all books
    @GetMapping("/books")
    public ResponseEntity<List<Book>> allBooks() {
        List<Book> list = this.bookServices.getBooks();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

    // get a book
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book book = this.bookServices.getBookById(id);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok().body(book);

    }

    // add a book
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        try {
            this.bookServices.addBook(book);
            System.out.println(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    // delete a book

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
        try {
            System.out.println("successfully deleted");
            this.bookServices.deleteBook(bookId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // update a book
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updatBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {

        try {
            this.bookServices.updatBook(book, bookId);
            System.out.println(book);
            return ResponseEntity.of(Optional.of(book));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
