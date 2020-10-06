package org.harvanir.demo.testcontainer.jpa.repository;

import org.harvanir.demo.testcontainer.configuration.JpaConfiguration;
import org.harvanir.demo.testcontainer.jpa.entity.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Harvan Irsyadi
 */
@Import(JpaConfiguration.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ItemRepositoryIT {

    @Autowired
    private ItemRepository itemRepository;

    @AfterEach
    void afterEach() {
        itemRepository.deleteAll();
    }

    @Test
    void givenEmpty_WhenFindAll_ThenFoundEmpty() {
        List<Item> all = itemRepository.findAll();

        Assertions.assertNotNull(all);
        Assertions.assertTrue(all.isEmpty());
    }

    private Item createRow() {
        Item item = new Item();
        item.setName("Name");
        item.setPrice(BigDecimal.TEN);
        item.setQuantity(10);

        itemRepository.save(item);

        return item;
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Test
    void givenSingleRow_WhenFindAll_ThenFoundSingleRow() {
        Item item = createRow();
        List<Item> all = itemRepository.findAll();

        Assertions.assertNotNull(all);
        Assertions.assertFalse(all.isEmpty());

        Item persist = all.get(0);

        Assertions.assertNotNull(persist.getId());
        Assertions.assertEquals(item.getName(), persist.getName());
        Assertions.assertEquals(item.getPrice().doubleValue(), persist.getPrice().doubleValue());
        Assertions.assertEquals(item.getQuantity(), persist.getQuantity());
        Assertions.assertNotNull(persist.getCreatedAt());
        Assertions.assertNotNull(persist.getUpdatedAt());
        Assertions.assertEquals(0, persist.getVersion());
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Test
    void givenExistingData_WhenSave_ThenVersionUpdated() {
        Item item = createRow();

        item.setPrice(item.getPrice().add(BigDecimal.TEN));
        item = itemRepository.save(item);

        Item persist = itemRepository.findById(item.getId()).orElse(null);

        Assertions.assertNotNull(persist);
        Assertions.assertEquals(item.getPrice().doubleValue(), persist.getPrice().doubleValue());
        Assertions.assertEquals(1, persist.getVersion());
    }
}