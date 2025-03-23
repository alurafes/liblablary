package rocks.alurafes.liblablary.model.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rocks.alurafes.liblablary.model.Root;
import rocks.alurafes.liblablary.model.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Author implements Root<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "author_id")
    private UUID id;
    @Column(name = "author_full_name")
    private String fullName;
    @OneToMany(orphanRemoval = true, mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Book> books = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY) // ManyToOne, so I can remove it easily, as this is a view and not a database table
    @JoinColumn(name = "author_id", insertable = false, updatable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private AuthorBookCount bookCount;
}
