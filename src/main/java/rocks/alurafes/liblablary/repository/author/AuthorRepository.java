package rocks.alurafes.liblablary.repository.author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import rocks.alurafes.liblablary.model.author.Author;
import rocks.alurafes.liblablary.model.book.Book;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID>, JpaSpecificationExecutor<Author> {
    @EntityGraph(attributePaths = {"bookCount"})
    @Override
    Optional<Author> findById(UUID uuid);

    @Override
    @EntityGraph(attributePaths = {"bookCount"})
    Page<Author> findAll(Specification<Author> spec, Pageable pageable);
}
