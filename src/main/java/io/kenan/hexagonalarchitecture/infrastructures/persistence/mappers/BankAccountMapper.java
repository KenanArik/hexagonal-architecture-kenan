package io.kenan.hexagonalarchitecture.infrastructures.persistence.mappers;

import io.kenan.hexagonalarchitecture.infrastructures.persistence.entities.BankAccountEntity;
import io.kenan.hexagonalarchitecture.application.domain.BankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface BankAccountMapper {
    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    BankAccount bankAccountEntityTobankAccountDto(BankAccountEntity book);

    BankAccountEntity bankAccountDtoTobankAccountEntity(BankAccount bankAccountDto);

}

