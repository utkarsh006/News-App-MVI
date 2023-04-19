package com.example.myapplication.base

interface LazilyStarted {

    /**
     * Accessing this variable starts an operation defined by the implementation
     */
    val start: Unit
}
