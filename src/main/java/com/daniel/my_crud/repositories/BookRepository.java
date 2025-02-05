package com.daniel.my_crud.repositories;

import com.daniel.my_crud.model.Book;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List; // Importa List

@Repository
public class BookRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final BookMapper bookMapper = new BookMapper(); // Corrección aquí

    public BookRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = namedParameterJdbcTemplate;
    }

    // Metodo para obtener todos los libros
    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM BOOKS";  // Consulta SQL
        return jdbcTemplate.query(sql, bookMapper);  // Usar bookMapper en lugar de BookMapper
    }

    // Mapper que convierte las filas del resultado en objetos Book
    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");  // Obtener el id de la columna "id"
            String name = rs.getString("name");  // Obtener el nombre de la columna "name"
            return new Book(id, name);  // Crear un objeto Book
        }
    }
}
