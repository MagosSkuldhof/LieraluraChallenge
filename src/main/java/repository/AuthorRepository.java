package com.tuusuario.literatura.repository;

import com.tuusuario.literatura.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    List<AuthorEntity> findByBirthYearLessThanEqualAndDeathYearGreaterThanEqualOrDeathYearIsNull(int yearStart, int yearEnd);
}
