package pl.coderslab.Dao;

import java.util.List;

public interface Memorable<E>
{
    List<E> findAll();

    E getById(Long id);

    void save(E e);

    void delete(Long id);
}
