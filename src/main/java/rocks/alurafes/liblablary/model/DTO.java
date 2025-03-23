package rocks.alurafes.liblablary.model;

public interface DTO<T> {
    T createBase();
    T updateBase(T base);
}
