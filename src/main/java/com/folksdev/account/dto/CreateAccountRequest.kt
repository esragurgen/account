package com.folksdev.account.dto;

import java.math.BigDecimal;

data class CreateAccountRequest(
    @field:NotBlank(message = "CustomerId must not be empty") //Boş olamaz
    val customerId: String,
    @field:Min(0, message = "Initial Credit value must not be negative value") //0 olamaz
    val initialCredit: BigDecimal
)

annotation class Min(val i: Int, val message: String)

annotation class NotBlank(val message: String)
