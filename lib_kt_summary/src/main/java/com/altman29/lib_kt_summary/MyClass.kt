package com.altman29.lib_kt_summary

//数据类型
data class Person(val name: String, val age: Int? = null)

fun main() {
    val persons = listOf<Person>(Person("Alice"), Person("Bob", 29), Person("Til"))
    val oldest = persons.maxByOrNull { it.age ?: 0 }
    println("The oldest is: $oldest")
}

/**
数据类 data class 自动生成toString方法
可空类型 Int? 实参的默认值
fun main 顶层函数
{it.age?:0} lambda表达式；Elvis运算符
 */