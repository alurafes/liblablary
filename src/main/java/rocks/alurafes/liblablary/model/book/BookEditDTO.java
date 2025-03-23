package rocks.alurafes.liblablary.model.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rocks.alurafes.liblablary.model.DTO;
import rocks.alurafes.liblablary.model.author.Author;
import rocks.alurafes.liblablary.model.ratingType.RatingType;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookEditDTO implements DTO<Book> {
    @NotBlank
    private String name;
    @NotNull
    private Integer pagesCount;
    private LocalDate readStartDate;
    private LocalDate readFinishDate;
    private String description;
    private String review;
    private UUID ratingTypeId;
    @Override
    public Book createBase() {
        return updateBase(new Book());
    }

    @Override
    public Book updateBase(Book base) {
        base.setName(this.getName());
        base.setPagesCount(this.getPagesCount());
        base.setReadStartDate(this.getReadStartDate());
        base.setReadFinishDate(this.getReadFinishDate());
        base.setDescription(this.getDescription());
        base.setReview(this.getReview());

        if (this.getRatingTypeId() != null) {
            RatingType ratingType = new RatingType();
            ratingType.setId(this.getRatingTypeId());
            base.setRatingType(ratingType);
        } else {
            base.setRatingType(null);
        }

        return base;
    }
}
