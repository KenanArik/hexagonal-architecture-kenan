package io.kenan.hexagonalarchitecture.infrastructures.configuration.transactional;

import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

public class TransactionalExecutor {
    @Transactional
    <T> T executeInTransaction(Supplier<T> execution) {
        return execution.get();
    }
}
