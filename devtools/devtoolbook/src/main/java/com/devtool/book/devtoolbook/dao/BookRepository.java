package com.devtool.book.devtoolbook.dao;

import org.springframework.data.repository.CrudRepository;

import com.devtool.book.devtoolbook.entities.Book;

public interface BookRepository extends CrudRepository<Book,Integer> {
    
    public Book findById(int id);
    
}
