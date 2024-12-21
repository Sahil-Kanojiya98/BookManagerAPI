package com.hello.BOOK_API.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookResponseDto {
    private Integer id;
    private String name;
    private int price;
    private String author;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate publicationDate;
}