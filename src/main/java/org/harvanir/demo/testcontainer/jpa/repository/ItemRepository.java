package org.harvanir.demo.testcontainer.jpa.repository;

import org.harvanir.demo.testcontainer.jpa.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Harvan Irsyadi
 */
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @NonNull
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    Item getOne(@NonNull Integer id);
}
