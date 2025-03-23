package rocks.alurafes.liblablary.controller.author;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.alurafes.liblablary.model.BasicResponse;
import rocks.alurafes.liblablary.model.DatabaseAction;
import rocks.alurafes.liblablary.model.author.Author;
import rocks.alurafes.liblablary.model.author.AuthorEditDTO;
import rocks.alurafes.liblablary.model.author.AuthorListDTO;
import rocks.alurafes.liblablary.service.author.AuthorService;
import rocks.alurafes.liblablary.util.filter.FilterService;

import java.util.UUID;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService service;
    private final FilterService filterService;

    public AuthorController(AuthorService service, FilterService filterService) {
        this.service = service;
        this.filterService = filterService;
    }

    @GetMapping
    public ResponseEntity<PagedModel<AuthorListDTO>> getPagedList(
            Pageable pageable,
            @RequestParam(name = "filter", required = false) String filterString
    ) {
        return ResponseEntity.ok(service.getPagedList(pageable, filterService.createFromString(filterString)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<BasicResponse<DatabaseAction<UUID>>> create(@Valid @RequestBody AuthorEditDTO editDTO) {
        return ResponseEntity.ok(service.create(editDTO));
    }

    @PostMapping("/{id}")
    public ResponseEntity<BasicResponse<DatabaseAction<UUID>>> edit(@PathVariable UUID id, @Valid @RequestBody AuthorEditDTO editDTO) {
        return ResponseEntity.ok(service.edit(id, editDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BasicResponse<DatabaseAction<UUID>>> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
