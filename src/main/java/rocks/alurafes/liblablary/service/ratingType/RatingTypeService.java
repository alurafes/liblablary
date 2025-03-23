package rocks.alurafes.liblablary.service.ratingType;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import rocks.alurafes.liblablary.model.BasicResponse;
import rocks.alurafes.liblablary.model.DatabaseAction;
import rocks.alurafes.liblablary.model.DatabaseActionType;
import rocks.alurafes.liblablary.model.author.Author;
import rocks.alurafes.liblablary.model.author.AuthorEditDTO;
import rocks.alurafes.liblablary.model.author.AuthorListDTO;
import rocks.alurafes.liblablary.model.ratingType.RatingType;
import rocks.alurafes.liblablary.model.ratingType.RatingTypeEditDTO;
import rocks.alurafes.liblablary.repository.author.AuthorRepository;
import rocks.alurafes.liblablary.repository.ratingType.RatingTypeRepository;
import rocks.alurafes.liblablary.util.filter.Filter;

import java.util.List;
import java.util.UUID;

@Service
public class RatingTypeService {
    private final RatingTypeRepository repository;

    public RatingTypeService(RatingTypeRepository repository) {
        this.repository = repository;
    }

    public List<RatingType> getList() {
        return repository.findAllByOrderByOrder();
    }

    public BasicResponse<DatabaseAction<UUID>> create(RatingTypeEditDTO editDTO) {
        return DatabaseAction.createResponse(DatabaseActionType.CREATED, repository.save(editDTO.createBase()));
    }

    public BasicResponse<DatabaseAction<UUID>> edit(UUID id, RatingTypeEditDTO editDTO) {
        RatingType entity = repository.findById(id).orElseThrow();
        return DatabaseAction.createResponse(DatabaseActionType.EDITED, repository.save(editDTO.updateBase(entity)));
    }

    public BasicResponse<DatabaseAction<UUID>> delete(UUID id) {
        RatingType entity = repository.findById(id).orElseThrow();
        repository.delete(entity);
        return DatabaseAction.createResponse(DatabaseActionType.DELETED, entity);
    }
}
