package site.coding.junitproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.coding.junitproject.domain.Book;
import site.coding.junitproject.domain.BookRepository;
import site.coding.junitproject.util.MailSender;
import site.coding.junitproject.web.dto.response.BookResponseDto;
import site.coding.junitproject.web.dto.request.BookSaveRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final MailSender mailSender;

    // 1.책등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResponseDto 책등록하기(BookSaveRequestDto dto) {
        Book bookPS = bookRepository.save(dto.toEntity());
        if (bookPS != null) {
            // 메일보내기 메서드 호출
            if (!mailSender.send()) {
                throw new RuntimeException("메일이 전송되지 않았습니다.");
            }
        }
        return bookPS.toDto();
    }

    // 2. 책목록보기
    public List<BookResponseDto> 책목록보기() {
        return bookRepository.findAll().stream()
//                .map(new BookResponseDto()::toDto) // new는 한번만되고 toDto가 두번 실행됨 그래서 같은 데이터를 리턴함
                .map(Book::toDto)
                .collect(Collectors.toList());
    }

    // 3. 책한건보기
    public BookResponseDto 책한권보기(Long id) {
        Optional<Book> bookOP = bookRepository.findById(id);
        if (bookOP.isPresent()) { //찾았다면
            Book bookPS = bookOP.get();
            return bookPS.toDto();
        } else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }

    // 4. 책삭제
    @Transactional(rollbackFor = RuntimeException.class)
    public void 책삭제하기(Long id) {
        bookRepository.deleteById(id);
    }

    // 5. 책수정
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResponseDto 책수정하기(Long id, BookSaveRequestDto dto) {
        Optional<Book> booOP = bookRepository.findById(id);
        if (booOP.isPresent()) {
            Book bookPS = booOP.get();
            bookPS.update(dto.getTitle(), dto.getAuthor()); //현재 리턴이 없어서 검증이 안되기대문에 return bookPS를 dto로 변환해서 리턴으로 변경
            return bookPS.toDto();
        } else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }
}
