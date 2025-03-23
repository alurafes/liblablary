package rocks.alurafes.liblablary.model.author;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rocks.alurafes.liblablary.model.DTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEditDTO implements DTO<Author> {
    @NotNull
    private String fullName;
    @Override
    public Author createBase() {
        return updateBase(new Author());
    }

    @Override
    public Author updateBase(Author base) {
        base.setFullName(this.getFullName());
        return base;
    }
}
