package io.kenan.hexagonalarchitecture.infrastructures.configuration.transactional;

import io.kenan.hexagonalarchitecture.application.ddd.DomainService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TransactionalDomainServiceAspect {
    private final TransactionalExecutor transactionalExecutor;

    public TransactionalDomainServiceAspect(TransactionalExecutor transactionalExecutor) {
        this.transactionalExecutor = transactionalExecutor;
    }

    @Pointcut("@within(domainService)")
    private void withinDomainService(DomainService domainService) {

    }

    @Around("withinDomainService(domainService)")
    private Object domainService(ProceedingJoinPoint proceedingJoinPoint,DomainService domainService ) {
        return transactionalExecutor.executeInTransaction(() -> {
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
    }

}
