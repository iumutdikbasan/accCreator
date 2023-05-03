package com.example.accaount.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class CustomerAccaountDto(
    val id: String,
    var balance: BigDecimal? = BigDecimal.ZERO,
    val transactions : Set<TransactionDto>?,
    val creationDate: LocalDateTime
)