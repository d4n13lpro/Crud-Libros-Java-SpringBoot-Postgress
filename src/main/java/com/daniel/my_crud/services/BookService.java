package com.daniel.my_crud.services;

import com.daniel.my_crud.model.Book;
import com.daniel.my_crud.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List; // Agregar la importación de List

@Service
public class BookService {
    private final BookRepository repository;

    // Constructor que inyecta BookRepository
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    // Metodo para obtener todos los libros
    public List<Book> getAllBooks() {
        return repository.getAllBooks();  // Llamar al repositorio para obtener los libros
    }
}
