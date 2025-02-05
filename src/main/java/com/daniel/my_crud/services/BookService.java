package com.daniel.my_crud.services;

import com.daniel.my_crud.model.Book;
import com.daniel.my_crud.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository repository;

    // Constructor para inyectar BookRepository
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    // Método para obtener todos los libros
    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    // Método para crear un nuevo libro
    public long createBook(Book newBook) {
        return repository.createBook(newBook);
    }

    // Método para actualizar un libro
    public void updateBook(long id, Book updatedBook) {
        repository.updateBook(id, updatedBook);  // Llamar al repositorio para actualizar
    }

    // Método para eliminar un libro
    public void deleteBook(long id) {
        repository.deleteBook(id);  // Llamar al repositorio para eliminar
    }
}
