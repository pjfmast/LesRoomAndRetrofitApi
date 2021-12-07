package com.example.lesroomandretrofitapi

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InventoryRepository: JpaRepository<InventoryItem, Int> {
}