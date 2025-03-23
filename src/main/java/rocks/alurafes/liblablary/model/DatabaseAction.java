package rocks.alurafes.liblablary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DatabaseAction<ID> {
    private DatabaseActionType type;
    private ID id;

    public static<ID> BasicResponse<DatabaseAction<ID>> createResponse(DatabaseActionType type, Root<ID> entity) {
        return new BasicResponse<>(new DatabaseAction<>(type, entity.getId()));
    }
}
