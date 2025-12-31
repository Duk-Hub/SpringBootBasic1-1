package com.validation.demo.controller;

import com.validation.demo.dto.CreateRequest;
import com.validation.demo.dto.Response;
import com.validation.demo.dto.UpdateRequest;
import com.validation.demo.repo.InMemoryRepo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
@Validated
public class NoticeController {

    private final InMemoryRepo inMemoryRepo;

    @PostMapping
    public ResponseEntity<Response> create(@RequestBody @Valid CreateRequest request) {
        inMemoryRepo.create(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(1)
                .toUri();

        return ResponseEntity.created(location).body(new Response("CREATED"));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable @Min(1) Integer id, @RequestBody @Valid UpdateRequest request) {
        inMemoryRepo.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("success"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> read(@PathVariable @Min(1) Integer id) {
        inMemoryRepo.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new Response("success"));
    }
}
