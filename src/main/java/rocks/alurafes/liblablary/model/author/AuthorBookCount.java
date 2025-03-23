package rocks.alurafes.liblablary.model.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "v_author_book_count")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AuthorBookCount {
    @Id
    @Column(name = "author_id", updatable = false, insertable = false)
    private UUID id;
    @JsonIgnore
    @Column(name = "author_book_count")
    private Integer bookCount;
}
