package com.daniel.my_crud.repositories;

import com.daniel.my_crud.model.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BookMapper bookMapper = new BookMapper();  // Corregido aquí
    private final SimpleJdbcInsert insert;
    private final String table = "books";

    // Constructor para inyectar NamedParameterJdbcTemplate y DataSource
    public BookRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource)
                .withTableName(table)
                .usingGeneratedKeyColumns("id");
    }

    // Método para obtener todos los libros
    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM " + table;  // Consulta SQL
        return jdbcTemplate.query(sql, bookMapper);  // Usar bookMapper
    }

    // Método para crear un libro
    public long createBook(Book newBook) {
        // Insertar el libro y retornar el ID generado
        return insert.executeAndReturnKey(
                new MapSqlParameterSource("name", newBook.name)  // Pasar el nombre del libro
        ).longValue();
    }

    // Método para actualizar un libro
    public void updateBook(long id, Book updatedBook) {
        String sql = "UPDATE " + table + " SET name = :name WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", updatedBook.name);
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    // Método para eliminar un libro
    public void deleteBook(long id) {
        String sql = "DELETE FROM " + table + " WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        jdbcTemplate.update(sql, params);
    }

    // Mapper para convertir filas en objetos Book
    private static class BookMapper implements org.springframework.jdbc.core.RowMapper<Book> {
        @Override
        public Book mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            return new Book(id, name);
        }
    }

}
