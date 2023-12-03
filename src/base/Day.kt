package base

import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.time.measureTimedValue

abstract class Day<T>(private val dayNumber: String) {
    open val test1Expected: T? = null
    open val test2Expected: T? = null
    open val part1Expected: T? = null
    open val part2Expected: T? = null

    internal abstract fun part1(input: List<String>): T
    internal abstract fun part2(input: List<String>): T

    fun run() {
        println()
        println("🎄🎁🎄🎁🎄🎁🎄Day $dayNumber 🎄🎁🎄🎁🎄🎁🎄️")
        println()
        println("**************   Test   **************")
        run(part = Part.One, isTest = true, expected = test1Expected)
        run(part = Part.Two, isTest = true, expected = test2Expected)
        println()
        println("**************  Actual  **************")
        run(part = Part.One, isTest = false, expected = part1Expected)
        run(part = Part.Two, isTest = false, expected = part2Expected)
    }

    private enum class Part(val value: String) {
        One("1"), Two("2");
    }

    private fun Part.block(input: List<String>) = when (this) {
        Part.One -> part1(input)
        Part.Two -> part2(input)
    }

    private val actualInput get() = Path("src/day$dayNumber/input.txt").readLines()

    private fun testInput(part: Part) = runCatching {
        val file = Path("src/day$dayNumber/test${part.value}.txt").readLines()
        file.ifEmpty {
            Path("src/day$dayNumber/test1.txt").readLines()
        }
    }.getOrElse {
        Path("src/day$dayNumber/test1.txt").readLines()
    }

    private fun run(part: Part, isTest: Boolean, expected: T?) {
        val input = if (isTest) testInput(part) else actualInput
        if (input.isEmpty()) {
            printResult(result = "❌Part $part: Input data is empty", time = null)
            return
        }
        val (value, time) = measureTimedValue { part.block(input) }
        when {
            expected == null -> printResult(result = "🌟Part $part: $value", time = time.inWholeMilliseconds)
            value == expected -> printResult(result = "✅Part $part: $value", time = time.inWholeMilliseconds)
            else -> {
                printResult(result = "❌Part $part: $value", time = time.inWholeMilliseconds)
                printResult(result = "❗️Expected: $expected", time = null)
            }
        }
    }

    private fun printResult(result: String, time: Long?) {
        val inputString = result.padEnd(28, ' ')
        val timeString = time?.let { " ⏳$it ms" } ?: ""
        println("$inputString$timeString")
    }
}