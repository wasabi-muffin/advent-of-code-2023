package day03

import base.Day

fun main() = Day03.run()

object Day03 : Day<Int>("03") {
    override val test1Expected: Int = 4361
    override val test2Expected: Int = 4678351
//    override val part1Expected: Int = 532331
//    override val part2Expected: Int = 823011201

    override fun part1(input: List<String>): Int {
        val data = parse(input = input)
        return data.numbers.sumOf { number ->
            if (number.boundingBox.contains(symbols = data.symbols)) number.value else 0
        }
    }

    override fun part2(input: List<String>): Int {
        val data = parse(input = input)
        return data.symbols.filter { it.value == '*' }.map { it.ratio(data.numbers) }.filter { it.count == 2 }.sumOf { it.value }
    }

    private fun parse(input: List<String>) = input.foldIndexed(Data()) { index, acc, line ->
        val newData = parseLine(index, line)
        acc.copy(
            numbers = acc.numbers + newData.numbers,
            symbols = acc.symbols + newData.symbols
        )
    }

    private fun parseLine(lineNumber: Int, line: String): Data = line.foldIndexed(Metadata()) { index, metadata, c ->
        when {
            c.isDigit() && index == line.lastIndex -> metadata.copy(
                data = metadata.data.copy(
                    numbers = metadata.updateNumbers(
                        number = (metadata.number + c).toInt(),
                        start = metadata.start ?: Point(x = index - 1, y = lineNumber),
                        end = Point(x = index - 1, y = lineNumber)
                    )
                ),
            )
            c.isDigit() && metadata.start != null -> metadata.copy(number = metadata.number + c)
            c.isDigit() && metadata.start == null -> metadata.copy(number = metadata.number + c, start = Point(x = index, y = lineNumber))
            metadata.start != null -> metadata.copy(
                number = "",
                start = null,
                data = metadata.data.copy(
                    numbers = metadata.updateNumbers(
                        number = metadata.number.toInt(),
                        start = metadata.start,
                        end = Point(x = index - 1, y = lineNumber)
                    ),
                    symbols = metadata.updateSymbols(c = c, point = Point(x = index, y = lineNumber))
                )
            )
            else -> metadata.copy(data = metadata.data.copy(symbols = metadata.updateSymbols(c = c, point = Point(x = index, y = lineNumber))))
        }
    }.data
}

data class Metadata(
    val number: String = "",
    val start: Point? = null,
    val data: Data = Data(),
) {
    fun updateNumbers(number: Int, start: Point, end: Point) = this.data.numbers + Number(value = number, start = start, end = end)

    fun updateSymbols(c: Char, point: Point) = if (c != '.') this.data.symbols + Symbol(value = c, point = point) else this.data.symbols
}

data class Data(
    val numbers: List<Number> = emptyList(),
    val symbols: List<Symbol> = emptyList(),
)

data class Point(val x: Int, val y: Int)

data class Box(val start: Point, val end: Point) {
    fun contains(point: Point): Boolean = point.x >= start.x && point.y >= start.y && point.x <= end.x && point.y <= end.y
    fun contains(symbols: List<Symbol>): Boolean = symbols.any { contains(it.point) }
}

data class Number(val value: Int, val start: Point, val end: Point) {
    val boundingBox
        get() = Box(
            start = Point(start.x - 1, start.y - 1),
            end = Point(end.x + 1, end.y + 1)
        )
}

data class Symbol(val value: Char, val point: Point) {
    fun ratio(numbers: List<Number>): Ratio = numbers.fold(initial = Ratio()) { ratio, number ->
        if (number.boundingBox.contains(point)) {
            Ratio(count = ratio.count + 1, value = ratio.value * number.value)
        } else {
            ratio
        }
    }

    inner class Ratio(val count: Int = 0, val value: Int = 1)
}