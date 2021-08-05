package com.dev.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.bookservice.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
