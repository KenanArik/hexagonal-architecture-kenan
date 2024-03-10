package io.kenan.hexagonalarchitecture.infrastructures.configuration;

import io.kenan.hexagonalarchitecture.application.ddd.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "io.kenan.hexagonalarchitecture.application",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {DomainService.class})}
)
public class DomainConfiguration {
}
