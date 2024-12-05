package io.github.imecuadorian.order.mapper;

public interface Mapper<T, R> {

    R mapEntityToDTO(T t);

    T mapDTOToEntity(R r);

}
