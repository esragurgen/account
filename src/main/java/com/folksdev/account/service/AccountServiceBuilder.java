package com.folksdev.account.service;

import com.folksdev.account.repository.AccountRepository;

import java.time.Clock;

public class AccountServiceBuilder {
    private AccountRepository accountRepository;
    private CustomerService customerService;
    private com.folksdev.account.dto.AccountDtoConverter accountDtoConverter;
    private Clock clock;

    public AccountServiceBuilder setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        return this;
    }

    public AccountServiceBuilder setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
        return this;
    }

    public AccountServiceBuilder setAccountDtoConverter(com.folksdev.account.dto.AccountDtoConverter accountDtoConverter) {
        this.accountDtoConverter = accountDtoConverter;
        return this;
    }

    public AccountServiceBuilder setClock(Clock clock) {
        this.clock = clock;
        return this;
    }

    public AccountService createAccountService() {
        return new AccountService(accountRepository, customerService, accountDtoConverter);
    }
}