package com.literalura.jmario.booksapp.repository;

import com.literalura.jmario.booksapp.model.AuthorData;
import com.literalura.jmario.booksapp.model.BookClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BooksRepository extends JpaRepository<AuthorData,Long> {
    List<BookClass> getAllTitles();
}
