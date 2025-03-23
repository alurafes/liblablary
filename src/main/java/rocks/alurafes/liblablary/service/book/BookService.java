package rocks.alurafes.liblablary.service.book;

import org.hibernate.Hibernate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rocks.alurafes.liblablary.model.BasicResponse;
import rocks.alurafes.liblablary.model.DatabaseAction;
import rocks.alurafes.liblablary.model.DatabaseActionType;
import rocks.alurafes.liblablary.model.book.*;
import rocks.alurafes.liblablary.repository.book.BookRepository;
import rocks.alurafes.liblablary.util.filter.Filter;

import java.io.IOException;
import java.sql.Blob;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class BookService {
    private final BookRepository repository;
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public PagedModel<BookListDTO> getPagedList(Pageable pageable, Filter filter) {
        return new PagedModel<>(repository.findAll(filter.createSpecification(), pageable).map(base -> {
            BookListDTO listDTO = new BookListDTO();
            listDTO.updateFromBase(base);
            return listDTO;
        }));
    }

    public PagedModel<BookListDTO> getPagedListByAuthorId(Pageable pageable, UUID authorId, Filter filter) {
        Specification<Book> specification = filter.<Book>createSpecification().and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("author").get("id"), authorId));
        return new PagedModel<>(repository.findAll(specification, pageable).map(base -> {
            BookListDTO listDTO = new BookListDTO();
            listDTO.updateFromBase(base);
            return listDTO;
        }));
    }

    public Book getById(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public BasicResponse<DatabaseAction<UUID>> create(BookCreateDTO editDTO) {
        return DatabaseAction.createResponse(DatabaseActionType.CREATED, repository.save(editDTO.createBase()));
    }

    public BasicResponse<DatabaseAction<UUID>> edit(UUID id, BookEditDTO editDTO) {
        Book entity = repository.findById(id).orElseThrow();
        return DatabaseAction.createResponse(DatabaseActionType.EDITED, repository.save(editDTO.updateBase(entity)));
    }

    public BasicResponse<DatabaseAction<UUID>> delete(UUID id) {
        Book entity = repository.findById(id).orElseThrow();
        repository.delete(entity);
        return DatabaseAction.createResponse(DatabaseActionType.DELETED, entity);
    }

    public BasicResponse<DatabaseAction<UUID>> uploadFile(UUID id, MultipartFile file) throws IOException {
        Book entity = repository.findById(id).orElseThrow();
        entity.setBookFile(new BookFile(file.getOriginalFilename(), file.getBytes()));
        repository.save(entity);
        return DatabaseAction.createResponse(DatabaseActionType.EDITED, entity);
    }

    public BookFile getBookFileById(UUID id) {
        Book entity = repository.findByIdWithBookFile(id).orElseThrow();
        if (entity.getBookFile() == null) {
            throw new NoSuchElementException();
        }
        return entity.getBookFile();
    }
}
