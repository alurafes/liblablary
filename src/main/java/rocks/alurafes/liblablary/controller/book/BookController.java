package rocks.alurafes.liblablary.controller.book;

import jakarta.validation.Valid;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rocks.alurafes.liblablary.model.BasicResponse;
import rocks.alurafes.liblablary.model.DatabaseAction;
import rocks.alurafes.liblablary.model.book.*;
import rocks.alurafes.liblablary.service.book.BookService;
import rocks.alurafes.liblablary.util.filter.FilterService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService service;
    private final FilterService filterService;

    public BookController(BookService service, FilterService filterService) {
        this.service = service;
        this.filterService = filterService;
    }

    @GetMapping
    public ResponseEntity<PagedModel<BookListDTO>> getPagedList(
            Pageable pageable,
            @RequestParam(name = "filter", required = false) String filterString
    ) {
        return ResponseEntity.ok(service.getPagedList(pageable, filterService.createFromString(filterString)));
    }

    @GetMapping("/by_author/{authorId}")
    public ResponseEntity<PagedModel<BookListDTO>> getPagedListByAuthorId(
            Pageable pageable,
            @PathVariable UUID authorId,
            @RequestParam(name = "filter", required = false) String filterString
    ) {
        return ResponseEntity.ok(service.getPagedListByAuthorId(pageable, authorId, filterService.createFromString(filterString)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<BasicResponse<DatabaseAction<UUID>>> create(@Valid @RequestBody BookCreateDTO editDTO) {
        return ResponseEntity.ok(service.create(editDTO));
    }

    @PostMapping("/{id}")
    public ResponseEntity<BasicResponse<DatabaseAction<UUID>>> edit(@PathVariable UUID id, @Valid @RequestBody BookEditDTO editDTO) {
        return ResponseEntity.ok(service.edit(id, editDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BasicResponse<DatabaseAction<UUID>>> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<BasicResponse<DatabaseAction<UUID>>> uploadFile(@PathVariable UUID id, @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(service.uploadFile(id, file));
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<ByteArrayResource> uploadFile(@PathVariable UUID id) throws IOException {
        BookFile bookFile = service.getBookFileById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + bookFile.getOriginalName() + "\"")
                .body(new ByteArrayResource(bookFile.getBookFileBlob().getFile()));
    }
}
