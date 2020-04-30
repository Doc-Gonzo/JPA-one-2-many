package com.example.jpa.controller;

import com.example.jpa.exception.ResourceNotFoundException;
import com.example.jpa.model.Poster;
import com.example.jpa.repository.PosterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PosterController {

    @Autowired
    private PosterRepository posterRepository;

    @GetMapping("/poster")
    public Page<Poster> getAllPosters(Pageable pageable) {
        return posterRepository.findAll(pageable);
    }

    @PostMapping("/poster")
    public Poster createPoster(@Valid @RequestBody Poster poster) {
        return posterRepository.save(poster);
    }

    @PutMapping("/poster/{posterId}")
    public Poster updatePoster(@PathVariable Long posterId, @Valid @RequestBody Poster posterRequest) {
        return posterRepository.findById(posterId).map(poster -> {
            poster.setUsername(posterRequest.getUsername());
            return posterRepository.save(poster);
        }).orElseThrow(() -> new ResourceNotFoundException("PosterId " + posterId + " not found"));
    }


    @DeleteMapping("/poster/{posterId}")
    public ResponseEntity<?> deletePost(@PathVariable Long posterId) {
        return posterRepository.findById(posterId).map(poster -> {
            posterRepository.delete(poster);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PosterId " + posterId + " not found"));
    }

}