package io.kenan.hexagonalarchitecture.application.services;

import io.kenan.hexagonalarchitecture.application.ddd.DomainService;
import io.kenan.hexagonalarchitecture.application.domain.BankAccount;
import io.kenan.hexagonalarchitecture.application.exceptions.InsufficientBalanceException;
import io.kenan.hexagonalarchitecture.application.port.incoming.BankAccountUseCase;
import io.kenan.hexagonalarchitecture.application.port.outgoing.BankAccountPort;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@DomainService
public class BankAccountService implements BankAccountUseCase {

    private final BankAccountPort bankAccountPort;

    public BankAccountService(BankAccountPort bankAccountPort) {
        this.bankAccountPort = bankAccountPort;
    }

    @Override
    public BankAccount openAccount(int id,BigDecimal initialBalance){
        BankAccount newAccount = new BankAccount(id,initialBalance);
        return bankAccountPort.save(newAccount);
    }

    @Override
    public BankAccount deposit(int id, BigDecimal amount) {
        BankAccount account = bankAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);

        account.setBalance(account.getBalance().add(amount));

        bankAccountPort.save(account);
        return account;
    }

    @Override
    public BankAccount withdraw(int id, BigDecimal amount) {
        BankAccount account = bankAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);

        account.setBalance(account.getBalance().subtract(amount));
        if(account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance for withdrawal.");
        }
        account = bankAccountPort.save(account);
        return account;
    }

    @Override
    public BankAccount getBankAccount(int id) {
        return bankAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
