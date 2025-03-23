package rocks.alurafes.liblablary.model.book;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rocks.alurafes.liblablary.model.Root;
import rocks.alurafes.liblablary.model.author.Author;
import rocks.alurafes.liblablary.model.ratingType.RatingType;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Book implements Root<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "book_id")
    private UUID id;
    @Column(name = "book_name")
    private String name;
    @Column(name = "book_pages_count")
    private Integer pagesCount;
    @Column(name = "book_read_start_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate readStartDate;
    @Column(name = "book_read_finish_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate readFinishDate;
    @Column(name = "book_description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "book_review", columnDefinition = "TEXT")
    private String review;
    @ManyToOne(optional = false)
    @JoinColumn(name = "book_author_id", nullable = false)
    private Author author;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_rating_type_id")
    private RatingType ratingType;
    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_book_file_id")
    private BookFile bookFile;
    @Transient
    private String bookFileOriginalName;

    @PostLoad
    public void postLoad() {
        if (this.getBookFile() != null) this.setBookFileOriginalName(this.getBookFile().getOriginalName());
    }
}
