package io.kenan.hexagonalarchitecture.application.parameterResolvers;

import io.kenan.hexagonalarchitecture.application.port.outgoing.BankAccountPort;
import io.kenan.hexagonalarchitecture.application.port.outgoing.stubs.InMemoryAccountsRepository;
import io.kenan.hexagonalarchitecture.application.services.BankAccountService;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class BankAccountServiceParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == BankAccountService.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
        BankAccountPort bankAccountPort = new InMemoryAccountsRepository();
        return new BankAccountService(bankAccountPort);
    }
}