package com.altman29.lib_kt_summary

//数据类型
data class Person(val name: String, val age: Int? = null)

fun main() {
    val persons = listOf<Person>(Person("Alice"), Person("Bob", 29))
    val oldest = persons.maxByOrNull { it.age ?: 0 }
    println("The oldest is: $oldest")
}