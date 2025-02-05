package com.daniel.my_crud.controllers;

import java.util.List;

import com.daniel.my_crud.model.Book;
import com.daniel.my_crud.services.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookService bookService;

    // Constructor para inyectar BookService
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    // Endpoint para obtener todos los libros
    @GetMapping("/book")
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    // Endpoint para crear un nuevo libro
    @PostMapping("/book")
    public long createBook(@RequestBody Book newBook){
        return bookService.createBook(newBook);  // Retornar el ID del libro creado
    }

    // Endpoint para actualizar un libro
    @PutMapping("/book/{id}")
    public void updateBook(@PathVariable long id, @RequestBody Book updatedBook){
        bookService.updateBook(id, updatedBook);  // Llamar al servicio para actualizar el libro
    }

    // Endpoint para eliminar un libro
    @DeleteMapping("/book/{id}")
    public void deleteBook(@PathVariable long id){
        bookService.deleteBook(id);  // Llamar al servicio para eliminar el libro
    }
}
