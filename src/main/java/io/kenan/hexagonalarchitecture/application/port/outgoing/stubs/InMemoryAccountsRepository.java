package io.kenan.hexagonalarchitecture.application.port.outgoing.stubs;

import io.kenan.hexagonalarchitecture.application.ddd.Stub;
import io.kenan.hexagonalarchitecture.application.domain.BankAccount;
import io.kenan.hexagonalarchitecture.application.port.outgoing.BankAccountPort;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Stub
public class InMemoryAccountsRepository implements BankAccountPort {
    private final Map<Integer, BankAccount> accounts = new HashMap<>();

    @Override
    public Optional<BankAccount> load(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        accounts.put(bankAccount.getId(), bankAccount);
        return bankAccount;
    }

}
