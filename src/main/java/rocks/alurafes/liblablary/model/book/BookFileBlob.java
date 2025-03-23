package rocks.alurafes.liblablary.model.book;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rocks.alurafes.liblablary.model.Root;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookFileBlob implements Root<UUID> {
    public BookFileBlob(byte[] file) {
        this.file = file;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "book_file_blob_id")
    private UUID id;
    @Column(name = "book_file_blob", columnDefinition = "BLOB")
    @Lob
    private byte[] file;
}
