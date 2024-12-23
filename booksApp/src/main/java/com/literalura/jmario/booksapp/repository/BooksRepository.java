package com.literalura.jmario.booksapp.repository;

import com.literalura.jmario.booksapp.model.BookClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface BooksRepository extends JpaRepository<BookClass,Long> {

    @Query("SELECT b FROM BookClass b")
    List<BookClass> findAllTitles();

    @Query(value = """
            SELECT\s
                a.author_name,
                STRING_AGG(DISTINCT a.id::TEXT, ',') AS ids,
                MIN(a.birth_year) AS birth_year,
                MIN(a.death_year) AS death_year,
                STRING_AGG(DISTINCT b.id::TEXT, ',') AS book_ids,
                STRING_AGG(DISTINCT b.title, ', ') AS book_titles
            FROM\s
                authors AS a
            LEFT JOIN\s
                books AS b
            ON\s
                a.book_id = b.id
            GROUP BY\s
                a.author_name;""",nativeQuery = true)
    List<Object[]> findAllAuthors();

}