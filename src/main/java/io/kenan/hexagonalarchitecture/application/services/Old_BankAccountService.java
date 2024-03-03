package io.kenan.hexagonalarchitecture.application.services;

import io.kenan.hexagonalarchitecture.application.ddd.DomainService;
import io.kenan.hexagonalarchitecture.application.domain.BankAccount;
import io.kenan.hexagonalarchitecture.application.port.incoming.DepositUseCase;
import io.kenan.hexagonalarchitecture.application.port.incoming.WithdrawUseCase;
import io.kenan.hexagonalarchitecture.application.port.outgoing.LoadAccountPort;
import io.kenan.hexagonalarchitecture.application.port.outgoing.SaveAccountPort;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@DomainService
public class Old_BankAccountService implements DepositUseCase, WithdrawUseCase {

    private final LoadAccountPort loadAccountPort;
    private final SaveAccountPort saveAccountPort;

    public Old_BankAccountService(LoadAccountPort loadAccountPort, SaveAccountPort saveAccountPort) {
        this.loadAccountPort = loadAccountPort;
        this.saveAccountPort = saveAccountPort;
    }

    @Override
    public BankAccount deposit(int id, BigDecimal amount) {
        BankAccount account = loadAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);

        account.deposit(amount);

        saveAccountPort.save(account);
        return account;
    }

    @Override
    public BankAccount withdraw(int id, BigDecimal amount) {
        BankAccount account = loadAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);


        return account.withdraw(amount);
    }

    public BankAccount getBankAccount(int id) {
        return loadAccountPort.load(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
