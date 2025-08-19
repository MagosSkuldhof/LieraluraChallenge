package com.tuusuario.literatura.repository;

import com.tuusuario.literatura.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    List<AuthorEntity> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqualOrDeathYearIsNull(int yearStart, int yearEnd);
}


public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    // Devuelve autores cuyo nacimiento <= year y (deathYear >= year o deathYear es null)
    List<AuthorEntity> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqualOrDeathYearIsNull(int year1, int year2);
}
