package com.hello.BOOK_API.service;

import com.hello.BOOK_API.dto.BookRequestDto;
import com.hello.BOOK_API.dto.BookResponseDto;
import com.hello.BOOK_API.model.Book;
import com.hello.BOOK_API.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;

    public List<BookResponseDto> getAllBooks() {
        return bookRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public BookResponseDto getBookById(Integer id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with ID " + id + " not found"));
        return toDto(book);
    }

    public BookResponseDto saveBook(BookRequestDto bookRequest) {
        Book book = toEntity(bookRequest);
        Book savedBook = bookRepo.save(book);
        return toDto(savedBook);
    }

    public List<BookResponseDto> saveAllBooks(List<BookRequestDto> bookRequests) {
        List<Book> books = bookRequests.stream().map(this::toEntity).collect(Collectors.toList());
        List<Book> savedBooks = bookRepo.saveAll(books);
        return savedBooks.stream().map(this::toDto).collect(Collectors.toList());
    }

    public BookResponseDto updateBook(Integer id, BookRequestDto bookRequest) {
        Book book = bookRepo.findById(id).orElseThrow(() -> new RuntimeException("Book with ID " + id + " not found"));
        Optional.ofNullable(bookRequest.getName()).ifPresent(book::setName);
        Optional.of(bookRequest.getPrice()).filter(price -> price > 0).ifPresent(book::setPrice);
        Optional.ofNullable(bookRequest.getAuthor()).ifPresent(book::setAuthor);
        Optional.ofNullable(bookRequest.getPublicationDate()).ifPresent(book::setPublicationDate);
        return toDto(bookRepo.save(book));
    }

    public void deleteBook(Integer id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book with ID " + id + " not found"));
        bookRepo.delete(book);
    }

    public List<BookResponseDto> searchBooks(String name, Integer minPrice, Integer maxPrice) {
        List<Book> books = bookRepo.searchByNameAndPriceRange(name, minPrice, maxPrice);
        return books.stream().map(this::toDto).collect(Collectors.toList());
    }

    private Book toEntity(BookRequestDto dto) {
        return Book.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .author(dto.getAuthor())
                .publicationDate(dto.getPublicationDate())
                .build();
    }

    private BookResponseDto toDto(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .name(book.getName())
                .price(book.getPrice())
                .author(book.getAuthor())
                .publicationDate(book.getPublicationDate())
                .build();
    }

}
