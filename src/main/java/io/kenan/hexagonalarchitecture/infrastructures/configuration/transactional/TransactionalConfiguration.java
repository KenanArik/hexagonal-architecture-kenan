package io.kenan.hexagonalarchitecture.infrastructures.configuration.transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class TransactionalConfiguration {
    @Bean
    TransactionalDomainServiceAspect transactionalDomainServiceAspect(TransactionalExecutor transactionalExecutor) {
        return new TransactionalDomainServiceAspect(transactionalExecutor);
    }

    @Bean
    TransactionalExecutor transactionalExecutor() {
        return new TransactionalExecutor();
    }
}