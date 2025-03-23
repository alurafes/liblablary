package rocks.alurafes.liblablary.controller.ratingType;

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
import rocks.alurafes.liblablary.model.ratingType.RatingType;
import rocks.alurafes.liblablary.model.ratingType.RatingTypeEditDTO;
import rocks.alurafes.liblablary.service.author.AuthorService;
import rocks.alurafes.liblablary.service.ratingType.RatingTypeService;
import rocks.alurafes.liblablary.util.filter.FilterService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rating_type")
public class RatingTypeController {
    private final RatingTypeService service;

    public RatingTypeController(RatingTypeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RatingType>> getList(
            Pageable pageable,
            @RequestParam(name = "filter", required = false) String filterString
    ) {
        return ResponseEntity.ok(service.getList());
    }

    @PostMapping
    public ResponseEntity<BasicResponse<DatabaseAction<UUID>>> create(@Valid @RequestBody RatingTypeEditDTO editDTO) {
        return ResponseEntity.ok(service.create(editDTO));
    }

    @PostMapping("/{id}")
    public ResponseEntity<BasicResponse<DatabaseAction<UUID>>> edit(@PathVariable UUID id, @Valid @RequestBody RatingTypeEditDTO editDTO) {
        return ResponseEntity.ok(service.edit(id, editDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BasicResponse<DatabaseAction<UUID>>> delete(@PathVariable UUID id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
