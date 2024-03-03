package io.kenan.hexagonalarchitecture.adapters.persistence.repositories;

import io.kenan.hexagonalarchitecture.adapters.persistence.entities.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountJpaRepository  extends JpaRepository<BankAccountEntity, Integer> {
}