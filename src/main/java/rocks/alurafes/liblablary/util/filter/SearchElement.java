package rocks.alurafes.liblablary.util.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SearchElement {
    private String[] path;
    private SearchType type;
    private String[] value;
}
