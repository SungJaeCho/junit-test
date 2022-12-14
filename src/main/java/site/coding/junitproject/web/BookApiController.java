package site.coding.junitproject.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.coding.junitproject.service.BookService;
import site.coding.junitproject.web.dto.response.BookResponseDto;
import site.coding.junitproject.web.dto.request.BookSaveRequestDto;
import site.coding.junitproject.web.dto.response.CMResponseDto;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    // 책등록
    @PostMapping
    public ResponseEntity<?> postBook(@RequestBody @Valid BookSaveRequestDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fe : bindingResult.getFieldErrors()) {
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            throw  new RuntimeException(errorMap.toString());
        }
        BookResponseDto bookResponseDto = bookService.책등록하기(dto);
        CMResponseDto<?> cmResponseDto = CMResponseDto.builder().code(1).message("책 등록 성공").body(bookResponseDto).build();
        return new ResponseEntity<>(cmResponseDto, HttpStatus.CREATED);
    }

    // 책목록보기
    public ResponseEntity<?> getBookList(){
        return null;
    }

    // 책한건보기
    public ResponseEntity<?> getBookOne(){
        return null;
    }

    // 책삭제하기
    public ResponseEntity<?> deleteBook(){
        return null;
    }

    // 책수정하기
    public ResponseEntity<?> updateBook(){
        return null;
    }
}
