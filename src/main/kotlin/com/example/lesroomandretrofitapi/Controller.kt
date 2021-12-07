package com.example.lesroomandretrofitapi

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class Controller(
    private val repository: InventoryRepository
) {
    private val log = LoggerFactory.getLogger(Controller::class.java)

    init {
        log.warn("You can access the swagger page on localhost:8080/swagger (probably)")
    }

    @GetMapping("/items/{id}")
    fun getById(
        @PathVariable id: Int
    ) = repository.findById(id)

    @GetMapping("/items")
    fun getAll() = repository.findAll()

    @PutMapping("/items/{id}")
    fun update(
        @PathVariable id: Int,
        @RequestBody inventoryItem: InventoryItem
    ): InventoryItem {
        require(inventoryItem.id == id)
//        return repository.saveAndFlush(inventoryItem)
        return if (repository.existsById(id)) {
            repository.saveAndFlush(
                InventoryItem(
                    id = inventoryItem.id,
                    itemName = inventoryItem.itemName,
                    itemPrice = inventoryItem.itemPrice,
                    quantityInStock = inventoryItem.quantityInStock
                )
            )
        } else throw InventoryItemNotFoundException(HttpStatus.NOT_FOUND, "No matching inventoryItem was found")
    }

    @PostMapping("/items")
    fun create(
        @RequestBody inventoryItem: InventoryItem
    ): InventoryItem {
        require(inventoryItem.id == null || inventoryItem.id == 0) // Android Room uses id=0 for new item
        return repository.saveAndFlush(inventoryItem)
    }

    @DeleteMapping("/items/{id}")
    fun delete(@PathVariable id: Int) = repository.deleteById(id)
}
