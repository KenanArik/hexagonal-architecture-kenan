package io.kenan.hexagonalarchitecture.application.port.outgoing;

import io.kenan.hexagonalarchitecture.application.domain.BankAccount;

import java.util.Optional;

public interface LoadAccountPort {
    Optional<BankAccount> load(int id);
}