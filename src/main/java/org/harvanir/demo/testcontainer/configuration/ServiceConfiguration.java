package org.harvanir.demo.testcontainer.configuration;

import org.harvanir.demo.testcontainer.jpa.repository.ItemRepository;
import org.harvanir.demo.testcontainer.service.DefaultItemService;
import org.harvanir.demo.testcontainer.service.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Harvan Irsyadi
 */
@Configuration(proxyBeanMethods = false)
public class ServiceConfiguration {

    @Bean
    public ItemService itemService(ItemRepository itemRepository) {
        return new DefaultItemService(itemRepository);
    }
}