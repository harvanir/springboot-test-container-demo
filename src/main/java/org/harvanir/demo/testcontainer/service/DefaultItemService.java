package org.harvanir.demo.testcontainer.service;

import org.harvanir.demo.testcontainer.domain.entity.ItemResponse;
import org.harvanir.demo.testcontainer.jpa.entity.Item;
import org.harvanir.demo.testcontainer.jpa.repository.ItemRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Harvan Irsyadi
 */
public class DefaultItemService implements ItemService {

    private final ItemRepository itemRepository;

    public DefaultItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private ItemResponse map(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .createdAt(item.getCreatedAt())
                .updatedAt(item.getUpdatedAt())
                .build();
    }

    @Override
    public List<ItemResponse> findAll() {
        return itemRepository.findAll()
                .stream().map(this::map)
                .collect(Collectors.toList());
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public ItemResponse findById(Integer id) {
        return map(itemRepository.getOne(id));
    }
}