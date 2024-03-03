package io.kenan.hexagonalarchitecture.application.port.incoming;

import io.kenan.hexagonalarchitecture.application.domain.BankAccount;

import java.math.BigDecimal;

public interface WithdrawUseCase {
    BankAccount withdraw(int id, BigDecimal amount);
}