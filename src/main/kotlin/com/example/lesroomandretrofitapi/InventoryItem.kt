package com.example.lesroomandretrofitapi

import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
@NoArgsConstructor
data class InventoryItem(
    @Id
    @GeneratedValue
    val id: Int?,
    val itemName: String,
    val itemPrice: Double,
    val quantityInStock: Int
)