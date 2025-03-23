package rocks.alurafes.liblablary.model.ratingType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rocks.alurafes.liblablary.model.DTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingTypeEditDTO implements DTO<RatingType> {
    private String name;
    private Integer order;

    @Override
    public RatingType createBase() {
        return updateBase(new RatingType());
    }

    @Override
    public RatingType updateBase(RatingType base) {
        base.setName(this.getName());
        base.setOrder(this.getOrder());
        return base;
    }
}
