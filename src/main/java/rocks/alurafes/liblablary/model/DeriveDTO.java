package rocks.alurafes.liblablary.model;

public interface DeriveDTO<T> {
    void updateFromBase(T base);
}
