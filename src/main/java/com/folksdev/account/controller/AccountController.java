package com.folksdev.account.controller;

import com.folksdev.account.dto.AccountDto;
import com.folksdev.account.dto.CreateAccountRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/account")

public class AccountController {
    private final com.folksdev.account.service.AccountService accountService;
    public AccountController(com.folksdev.account.service.AccountService accountService){
        this.accountService = accountService;

        //Özetlemek gerekirse, POST yeni kaynaklar oluşturmak için
        // kullanılırken, PUT var olan kaynakları güncellemek (veya oluşturmak) için kullanılır.
    }

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody CreateAccountRequest request){
        return ResponseEntity.ok(accountService.createAccount(request));
    }

}
