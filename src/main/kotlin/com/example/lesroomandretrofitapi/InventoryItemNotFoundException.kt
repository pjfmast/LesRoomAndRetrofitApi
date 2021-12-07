package com.example.lesroomandretrofitapi

import org.springframework.http.HttpStatus

class InventoryItemNotFoundException (val statusCode: HttpStatus, val reason: String) : Exception()