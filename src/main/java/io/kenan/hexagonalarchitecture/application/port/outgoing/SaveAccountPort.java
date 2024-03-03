package io.kenan.hexagonalarchitecture.application.port.outgoing;

import io.kenan.hexagonalarchitecture.application.domain.BankAccount;

public interface SaveAccountPort {
    void save(BankAccount bankAccount);
}