package rocks.alurafes.liblablary.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BasicResponse<T_WHAT> {
    public BasicResponse(T_WHAT what, Instant when) {
        this.what = what;
        this.when = when;
    }

    public BasicResponse(T_WHAT what) {
        this.what = what;
        this.setWhen(Instant.now());
    }

    private T_WHAT what;
    private Instant when;
}
