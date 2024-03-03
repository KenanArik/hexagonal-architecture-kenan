package io.kenan.hexagonalarchitecture.application.port.incoming;

import io.kenan.hexagonalarchitecture.application.domain.BankAccount;

import java.math.BigDecimal;

public interface DepositUseCase {
    BankAccount deposit(int id, BigDecimal amount);
}