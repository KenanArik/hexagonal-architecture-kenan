package io.kenan.hexagonalarchitecture.application.services;

import io.kenan.hexagonalarchitecture.application.ddd.DomainService;
import io.kenan.hexagonalarchitecture.application.domain.BankAccount;
import io.kenan.hexagonalarchitecture.application.port.incoming.DepositUseCase;
import io.kenan.hexagonalarchitecture.application.port.incoming.WithdrawUseCase;
import io.kenan.hexagonalarchitecture.application.port.outgoing.BankAccountPort;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@DomainService
public class BankAccountService implements DepositUseCase, WithdrawUseCase {

    private final BankAccountPort bankAccountPort;

    public BankAccountService(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    public BankAccount openAccount(int id,BigDecimal initialBalance){
        BankAccount newAccount = new BankAccount(id,initialBalance);
        return bankAccountPort.save(newAccount);
    }

    @Override
    public BankAccount deposit(int id, BigDecimal amount) {
        BankAccount account = bankAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);

        account.deposit(amount);

        bankAccountPort.save(account);
        return account;
    }

    @Override
    public BankAccount withdraw(int id, BigDecimal amount) {
        BankAccount account = bankAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);

        return account.withdraw(amount);
    }

    public BankAccount getBankAccount(int id) {
        return bankAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
