package com.daniel.my_crud;

import com.daniel.my_crud.model.Book;
import com.daniel.my_crud.services.BookService;
import com.daniel.my_crud.controllers.BookController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

	@Mock
	private BookService bookService;  // Usamos @Mock de Mockito

	@InjectMocks
	private BookController bookController;  // Usamos @InjectMocks para inyectar el mock en el controlador

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);  // Inicializamos los mocks
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();  // Configuramos MockMvc
	}

	@Test
	void testGetAllBooks() throws Exception {
		System.out.println("Ejecutando testGetAllBooks...");

		List<Book> books = Arrays.asList(new Book(1, "Book 1"), new Book(2, "Book 2"));
		when(bookService.getAllBooks()).thenReturn(books);

		mockMvc.perform(get("/book"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].name").value("Book 1"))
				.andExpect(jsonPath("$[1].name").value("Book 2"));

		verify(bookService, times(1)).getAllBooks();  // Verificamos que el servicio fue llamado una vez

		System.out.println("testGetAllBooks completado.");
	}

	@Test
	void testCreateBook() throws Exception {
		System.out.println("Ejecutando testCreateBook...");

		Book newBook = new Book(1, "New Book");
		when(bookService.createBook(any(Book.class))).thenReturn(newBook.id);

		mockMvc.perform(post("/book")
						.contentType("application/json")
						.content("{\"name\":\"New Book\"}"))
				.andExpect(status().isOk())
				.andExpect(content().string("1"));

		verify(bookService, times(1)).createBook(any(Book.class));  // Verificamos que se llamó al método createBook

		System.out.println("testCreateBook completado.");
	}

	@Test
	void testUpdateBook() throws Exception {
		System.out.println("Ejecutando testUpdateBook...");

		Book updatedBook = new Book(1, "Updated Book");

		mockMvc.perform(put("/book/{id}", 1)
						.contentType("application/json")
						.content("{\"name\":\"Updated Book\"}"))
				.andExpect(status().isOk());

		verify(bookService, times(1)).updateBook(eq(1L), any(Book.class));  // Verificamos que se llamó al método updateBook

		System.out.println("testUpdateBook completado.");
	}

	@Test
	void testDeleteBook() throws Exception {
		System.out.println("Ejecutando testDeleteBook...");

		mockMvc.perform(delete("/book/{id}", 1))
				.andExpect(status().isOk());

		verify(bookService, times(1)).deleteBook(1L);  // Verificamos que se llamó al método deleteBook

		System.out.println("testDeleteBook completado.");
	}
}
