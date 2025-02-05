package com.daniel.my_crud.controllers;

import java.util.List;
import com.daniel.my_crud.model.Book;
import com.daniel.my_crud.services.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    @GetMapping("/book")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }
}
