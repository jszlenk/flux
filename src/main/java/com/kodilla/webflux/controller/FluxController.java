package com.kodilla.webflux.controller;

import com.kodilla.webflux.BookDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE;

@RestController
public class FluxController {

    @GetMapping(value = "/strings", produces = APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> getStrings() {
        return Flux
                .just("a", "b", "c", "d", "e")
                .delayElements(Duration.ofSeconds(2))
                .log();
    }

    @GetMapping(value = "/books", produces = APPLICATION_STREAM_JSON_VALUE)
    public Flux<BookDto> getBooks() {
        List<BookDto> books = List.of(
                new BookDto("The Catcher in the Rye", "J.D. Salinger", 1951),
                new BookDto("To Kill a Mockingbird", "Harper Lee", 1960),
                new BookDto("1984", "George Orwell", 1949),
                new BookDto("Pride and Prejudice", "Jane Austen", 1813),
                new BookDto("The Great Gatsby", "F. Scott Fitzgerald", 1925)
        );

        return Flux.fromIterable(books)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

}
