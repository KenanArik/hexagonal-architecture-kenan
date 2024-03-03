package io.kenan.hexagonalarchitecture.application.port.outgoing;

import io.kenan.hexagonalarchitecture.application.domain.BankAccount;

import java.util.Optional;

public interface BankAccountPort {
    Optional<BankAccount> load(int id);
    BankAccount save(BankAccount bankAccount);

}