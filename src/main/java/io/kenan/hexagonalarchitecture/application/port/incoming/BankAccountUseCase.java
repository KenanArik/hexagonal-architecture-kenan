package io.kenan.hexagonalarchitecture.application.port.incoming;

import io.kenan.hexagonalarchitecture.application.domain.BankAccount;

import java.math.BigDecimal;

public interface BankAccountUseCase {
    BankAccount withdraw(int id, BigDecimal amount);
    BankAccount deposit(int id, BigDecimal amount);
    BankAccount openAccount(int id,BigDecimal initialBalance);
    public BankAccount getBankAccount(int id);
}