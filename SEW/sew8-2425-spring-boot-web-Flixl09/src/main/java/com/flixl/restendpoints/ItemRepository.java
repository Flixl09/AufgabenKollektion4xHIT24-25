package com.flixl.restendpoints;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    boolean existsItemById(Long id);
    List<Item> findAllByOrderByIdAsc();
}
