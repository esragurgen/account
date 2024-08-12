package com.folksdev.account.dto;

import com.folksdev.account.dto.CustomerDtoConverter;
import com.folksdev.account.model.Account;
import com.folksdev.account.dto.AccountDto;
import org.springframework.stereotype.Component;
import com.folksdev.account.model.Transaction;


import java.util.stream.Collectors;

@Component
public class AccountDtoConverter {

    private final CustomerDtoConverter customerDtoConverter;
    private final com.folksdev.account.dto.TransactionDtoConverter transactionDtoConverter;

    public AccountDtoConverter(CustomerDtoConverter customerDtoConverter,
                               com.folksdev.account.dto.TransactionDtoConverter transactionDtoConverter) {
        this.customerDtoConverter = customerDtoConverter;
        this.transactionDtoConverter = transactionDtoConverter;
    }

    public AccountDto convert(Account from){
        return new AccountDto(from.getId(),
                from.getBalance(),
                from.getCreationDate(),
                customerDtoConverter.convertToAccountCustomer(from.getCustomer()),
                from.getTransaction().stream().map(t -> transactionDtoConverter.convert(t)).collect(Collectors.toSet()));
    }
}