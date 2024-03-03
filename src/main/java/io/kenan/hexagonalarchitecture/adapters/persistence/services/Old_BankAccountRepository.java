package io.kenan.hexagonalarchitecture.adapters.persistence.services;

import io.kenan.hexagonalarchitecture.adapters.persistence.entities.BankAccountEntity;
import io.kenan.hexagonalarchitecture.adapters.persistence.mappers.BankAccountMapper;
import io.kenan.hexagonalarchitecture.adapters.persistence.repositories.BankAccountJpaRepository;
import io.kenan.hexagonalarchitecture.application.domain.BankAccount;
import io.kenan.hexagonalarchitecture.application.port.outgoing.LoadAccountPort;
import io.kenan.hexagonalarchitecture.application.port.outgoing.SaveAccountPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Old_BankAccountRepository implements LoadAccountPort, SaveAccountPort {

    private final BankAccountJpaRepository repository;

    public Old_BankAccountRepository(BankAccountJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<BankAccount> load(int id) {
        Optional<BankAccountEntity>  bankAccountEntity = repository.findById(id);

        return Optional.ofNullable(bankAccountEntity.map(BankAccountMapper.INSTANCE::bankAccountEntityTobankAccountDto).orElse(null));

    }

    @Override
    public void save(BankAccount bankAccount) {
        BankAccountEntity account = BankAccountMapper.INSTANCE.bankAccountDtoTobankAccountEntity(bankAccount);
        repository.save(account);
    }
}

