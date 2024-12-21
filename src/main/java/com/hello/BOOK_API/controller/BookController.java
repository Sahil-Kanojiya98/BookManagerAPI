package com.hello.BOOK_API.controller;

import com.hello.BOOK_API.dto.BookRequestDto;
import com.hello.BOOK_API.dto.BookResponseDto;
import com.hello.BOOK_API.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> saveBook(@RequestBody BookRequestDto bookRequest) {
        return ResponseEntity.ok(bookService.saveBook(bookRequest));
    }

    @PostMapping("/addAll")
    public ResponseEntity<List<BookResponseDto>> addAllBooks(@RequestBody List<BookRequestDto> bookRequests) {
        return ResponseEntity.ok(bookService.saveAllBooks(bookRequests));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Integer id, @RequestBody BookRequestDto bookRequest) {
        return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponseDto>> searchBooks(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice) {
        return ResponseEntity.ok(bookService.searchBooks(name, minPrice, maxPrice));
    }

}
