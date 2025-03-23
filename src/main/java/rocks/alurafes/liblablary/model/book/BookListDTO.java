package rocks.alurafes.liblablary.model.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rocks.alurafes.liblablary.model.DTO;
import rocks.alurafes.liblablary.model.DeriveDTO;
import rocks.alurafes.liblablary.model.author.AuthorListDTO;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookListDTO implements DeriveDTO<Book> {
    private UUID id;
    private String name;
    private Integer pagesCount;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate readStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate readFinishDate;
    private String description;
    private String review;
    private UUID authorId;
    private String authorFullName;
    private String ratingTypeName;
    private Boolean hasBookFile;

    @Override
    public void updateFromBase(Book base) {
        this.setId(base.getId());
        this.setName(base.getName());
        this.setPagesCount(base.getPagesCount());
        this.setReadStartDate(base.getReadStartDate());
        this.setReadFinishDate(base.getReadFinishDate());
        this.setDescription(base.getDescription());
        this.setReview(base.getReview());
        Optional.ofNullable(base.getAuthor()).ifPresent(author -> {
            this.setAuthorId(author.getId());
            this.setAuthorFullName(author.getFullName());
        });
        Optional.ofNullable(base.getRatingType()).ifPresent(ratingType -> {
            this.setRatingTypeName(ratingType.getName());
        });
        this.setHasBookFile(base.getBookFileOriginalName() != null);
    }
}
