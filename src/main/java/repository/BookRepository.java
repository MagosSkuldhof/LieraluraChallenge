package com.tuusuario.literatura.repository;

import com.tuusuario.literatura.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByTitleContainingIgnoreCase(String title);
    List<BookEntity> findByAuthor_NameContainingIgnoreCase(String authorName);
    List<BookEntity> findByLanguageIgnoreCase(String language);
}

