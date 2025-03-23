package rocks.alurafes.liblablary.repository.ratingType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import rocks.alurafes.liblablary.model.book.Book;
import rocks.alurafes.liblablary.model.ratingType.RatingType;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingTypeRepository extends JpaRepository<RatingType, UUID>, JpaSpecificationExecutor<RatingType> {
    List<RatingType> findAllByOrderByOrder();
}
