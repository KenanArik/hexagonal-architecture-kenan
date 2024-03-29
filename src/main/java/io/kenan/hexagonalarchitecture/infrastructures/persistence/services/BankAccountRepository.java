package io.kenan.hexagonalarchitecture.infrastructures.persistence.services;

import io.kenan.hexagonalarchitecture.infrastructures.persistence.entities.BankAccountEntity;
import io.kenan.hexagonalarchitecture.infrastructures.persistence.mappers.BankAccountMapper;
import io.kenan.hexagonalarchitecture.infrastructures.persistence.repositories.BankAccountJpaRepository;
import io.kenan.hexagonalarchitecture.application.domain.BankAccount;
import io.kenan.hexagonalarchitecture.application.port.outgoing.BankAccountPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BankAccountRepository implements BankAccountPort {

    private final BankAccountJpaRepository repository;

    public BankAccountRepository(BankAccountJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<BankAccount> load(int id) {
        Optional<BankAccountEntity>  bankAccountEntity = repository.findById(id);

        return Optional.ofNullable(bankAccountEntity.map(BankAccountMapper.INSTANCE::bankAccountEntityTobankAccountDto).orElse(null));

    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        BankAccountEntity account = BankAccountMapper.INSTANCE.bankAccountDtoTobankAccountEntity(bankAccount);
        return repository.save(account).toDomain();
    }
}

