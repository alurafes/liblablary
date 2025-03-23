package rocks.alurafes.liblablary.repository.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rocks.alurafes.liblablary.model.book.Book;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book> {
    @EntityGraph(attributePaths = {"author.bookCount", "ratingType", "bookFile"})
    @Override
    Optional<Book> findById(UUID uuid);

    @Override
    @EntityGraph(attributePaths = {"author.bookCount", "ratingType", "bookFile"})
    Page<Book> findAll(Specification<Book> spec, Pageable pageable);
    @EntityGraph(attributePaths = {"bookFile.bookFileBlob"})
    @Query("select b from Book b where b.id = ?1")
    Optional<Book> findByIdWithBookFile(UUID uuid);
}
