package com.example.jpa.repository;

import com.example.jpa.model.Post;
import com.example.jpa.model.Poster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PosterRepository extends JpaRepository<Poster, Long> {


}
