package site.coding.junitproject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.coding.junitproject.service.BookService;
import site.coding.junitproject.web.dto.BookResponseDto;
import site.coding.junitproject.web.dto.BookSaveRequestDto;

@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    @PostMapping
    public BookResponseDto postBook(@RequestBody BookSaveRequestDto dto) {
        BookResponseDto response = bookService.책등록하기(dto);
        return response;
    }
}
