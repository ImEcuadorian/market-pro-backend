package io.github.imecuadorian.product.mapper;

public interface Mapper<T, R> {

    R mapEntityToDTO(T t);

    T mapDTOToEntity(R r);

}
