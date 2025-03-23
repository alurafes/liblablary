package rocks.alurafes.liblablary.model.author;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rocks.alurafes.liblablary.model.DTO;
import rocks.alurafes.liblablary.model.DeriveDTO;

import java.util.Optional;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorListDTO implements DeriveDTO<Author> {
    private UUID id;
    private String fullName;
    private Integer bookCount = 0;
    @Override
    public void updateFromBase(Author base) {
        this.setId(base.getId());
        this.setFullName(base.getFullName());
        Optional.ofNullable(base.getBookCount()).ifPresent(bookCount -> {
            this.setBookCount(bookCount.getBookCount());
        });
    }
}
