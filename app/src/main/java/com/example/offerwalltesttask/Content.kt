package com.example.offerwalltesttask

sealed interface Content {
    val id: Int
    val type: String
}