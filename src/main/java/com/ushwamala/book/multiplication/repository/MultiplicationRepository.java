package com.ushwamala.book.multiplication.repository;

import com.ushwamala.book.multiplication.domain.Multiplication;
import org.springframework.data.repository.CrudRepository;

/**
 * This interface allows us to save and retrieve
 Multiplications
 */
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long> {
}
