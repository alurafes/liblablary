package rocks.alurafes.liblablary.model.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.context.annotation.Lazy;
import rocks.alurafes.liblablary.model.Root;

import java.io.InputStream;
import java.sql.Blob;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookFile implements Root<UUID> {
    public BookFile(String originalName, byte[] file) {
        this.originalName = originalName;
        this.bookFileBlob = new BookFileBlob(file);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "book_file_id")
    private UUID id;
    @Column(name = "book_file_original_name")
    private String originalName;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    @JoinColumn(name = "book_file_blob_id")
    private BookFileBlob bookFileBlob;
}
