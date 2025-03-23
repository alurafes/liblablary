package rocks.alurafes.liblablary.service.author;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rocks.alurafes.liblablary.model.BasicResponse;
import rocks.alurafes.liblablary.model.DatabaseAction;
import rocks.alurafes.liblablary.model.DatabaseActionType;
import rocks.alurafes.liblablary.model.author.Author;
import rocks.alurafes.liblablary.model.author.AuthorEditDTO;
import rocks.alurafes.liblablary.model.author.AuthorListDTO;
import rocks.alurafes.liblablary.repository.author.AuthorRepository;
import rocks.alurafes.liblablary.util.filter.Filter;

import java.util.UUID;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public PagedModel<AuthorListDTO> getPagedList(Pageable pageable, Filter filter) {
        return new PagedModel<>(repository.findAll(filter.createSpecification(), pageable).map(base -> {
            AuthorListDTO listDTO = new AuthorListDTO();
            listDTO.updateFromBase(base);
            return listDTO;
        }));
    }

    public Author getById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public BasicResponse<DatabaseAction<UUID>> create(AuthorEditDTO editDTO) {
        return DatabaseAction.createResponse(DatabaseActionType.CREATED, repository.save(editDTO.createBase()));
    }

    public BasicResponse<DatabaseAction<UUID>> edit(UUID id, AuthorEditDTO editDTO) {
        Author entity = repository.findById(id).orElseThrow();
        return DatabaseAction.createResponse(DatabaseActionType.EDITED, repository.save(editDTO.updateBase(entity)));
    }

    @Transactional
    public BasicResponse<DatabaseAction<UUID>> delete(UUID id) {
        Author entity = repository.findById(id).orElseThrow();
        repository.delete(entity);
        return DatabaseAction.createResponse(DatabaseActionType.DELETED, entity);
    }
}
