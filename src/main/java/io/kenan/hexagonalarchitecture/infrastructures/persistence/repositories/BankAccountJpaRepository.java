package io.kenan.hexagonalarchitecture.infrastructures.persistence.repositories;

import io.kenan.hexagonalarchitecture.infrastructures.persistence.entities.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountJpaRepository  extends JpaRepository<BankAccountEntity, Integer> {
}