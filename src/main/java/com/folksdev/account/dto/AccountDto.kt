package com.folksdev.account.dto;

import com.folksdev.account.dto.TransactionDto
import java.math.BigDecimal
import java.time.LocalDateTime

data class AccountDto(
    val id: String?,
    val balance: BigDecimal?  = BigDecimal.ZERO,
    val creationDate: LocalDateTime,
    val customer: AccountCustomerDto?,
    val transactions: Set<TransactionDto>?


)
