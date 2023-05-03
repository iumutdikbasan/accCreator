package com.example.accaount.dto

import java.math.BigDecimal
import java.time.LocalDateTime

data class AccaountDto(
    val id: String?,
    val balance: BigDecimal? = BigDecimal.ZERO,
    val creationDate: LocalDateTime,
    val customer: AccaountCustomerDto?,
    val transactions: Set<TransactionDto>?
)
