package com.folksdev.account.controller;

import static org.junit.runner.RunWith;

import com.folksdev.account.dto.AccountDtoConverter;
import com.folksdev.account.repository.AccountRepository;
import com.folksdev.account.service.AccountService;
import com.folksdev.account.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.function.Supplier;
import java.util.UUID;
import java.time.Clock;
import org.junit.runner.RunWith;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext

class AccountControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Clock clock;

    @MockBean
    private Supplier<UUID> uuidSupplier;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountDtoConverter accountDtoConverter;

    @Autowired
    private AccountService service = new AccountService(accountRepository, customerService, converter);



}
