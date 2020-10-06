package org.harvanir.demo.testcontainer.entrypoint.controller;

import org.harvanir.demo.testcontainer.domain.entity.ItemResponse;
import org.harvanir.demo.testcontainer.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Harvan Irsyadi
 */
@RequestMapping(ApiConstant.ITEM)
@RestController
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemResponse> findAll() {
        return itemService.findAll();
    }

    @GetMapping(ApiConstant.ID)
    public ItemResponse findById(@PathVariable Integer id) {
        return itemService.findById(id);
    }
}