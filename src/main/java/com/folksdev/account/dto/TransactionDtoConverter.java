package com.folksdev.account.dto;
import org.springframework.stereotype.Component;
// Doğru import bu olmalı
import com.folksdev.account.model.Transaction;

@Component
public class TransactionDtoConverter {

    public TransactionDto convert(Transaction from){
        return new TransactionDto(from.getId(),
                from.getTransactionType(),
                from.getAmount(),
                from.getTransactionDate());
    }
}
