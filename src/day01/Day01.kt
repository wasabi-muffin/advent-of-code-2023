package day01

import base.Day

fun main() = Day01.run()

object Day01 : Day<Int>("01") {
    override val test1Expected: Int = 142
    override val test2Expected: Int = 281

    override fun part1(input: List<String>): Int = input.sumOf { line ->
        line.first { it.isDigit() }.digitToInt() * 10 +
            line.last { it.isDigit() }.digitToInt()
    }

    override fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val firstWord = line.findAnyOf(strings = words, ignoreCase = true)?.toIndexedValue()
            val lastWord = line.findLastAnyOf(strings = words, ignoreCase = true)?.toIndexedValue()
            val firstDigit = line.withIndex().firstOrNull { it.value.isDigit() }
            val lastDigit = line.withIndex().lastOrNull { it.value.isDigit() }
            getNumber(firstWord, firstDigit) { word, digit -> digit < word } * 10 +
                getNumber(lastWord, lastDigit) { word, digit -> word < digit }
        }
    }

    private val words: List<String> = listOf(
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine",
    )

    private fun getNumber(word: IndexedValue<String>?, digit: IndexedValue<Char>?, useDigitWhen: (word: Int, digit: Int) -> Boolean) = when {
        word == null && digit == null -> 0
        digit == null -> words.indexOf(word?.value) + 1
        word == null || useDigitWhen(word.index, digit.index) -> digit.value.digitToInt()
        else -> words.indexOf(word.value) + 1
    }

    private fun <T> Pair<Int, T>?.toIndexedValue(): IndexedValue<T>? {
        if (this == null) return null
        return IndexedValue(first, second)
    }
}