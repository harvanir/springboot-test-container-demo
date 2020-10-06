package org.harvanir.demo.testcontainer.configuration;

import org.harvanir.demo.testcontainer.jpa.entity.EntityMarker;
import org.harvanir.demo.testcontainer.jpa.repository.RepositoryMarker;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Harvan Irsyadi
 */
@EnableJpaAuditing
@EnableJpaRepositories(basePackageClasses = RepositoryMarker.class)
@EntityScan(basePackageClasses = EntityMarker.class)
@Configuration(proxyBeanMethods = false)
public class JpaConfiguration {
}