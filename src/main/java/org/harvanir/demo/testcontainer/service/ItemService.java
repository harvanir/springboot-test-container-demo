package org.harvanir.demo.testcontainer.service;

import org.harvanir.demo.testcontainer.domain.entity.ItemResponse;

import java.util.List;

/**
 * @author Harvan Irsyadi
 */
public interface ItemService {

    List<ItemResponse> findAll();

    ItemResponse findById(Integer id);
}