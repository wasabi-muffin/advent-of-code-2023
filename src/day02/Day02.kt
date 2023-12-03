package day02

import base.Day
import base.filterDigits
import base.filterLetters

fun main() = Day02.run()

object Day02 : Day<Int>("02") {
    override val test1Expected: Int = 8
    override val test2Expected: Int = 2286

    override fun part1(input: List<String>): Int {
        return input.map { Game(it) }.sumOf { game ->
            if (game.turns.any { !it.isValid(maxRed = 12, maxGreen = 13, maxBlue = 14) }) 0 else game.id
        }
    }

    override fun part2(input: List<String>): Int {
        return input.map { Game(it) }.sumOf { game ->
            game.turns.fold(Turn(red = 0, green = 0, blue = 0)) { max, turn ->
                Turn(
                    red = maxOf(max.red, turn.red),
                    green = maxOf(max.green, turn.green),
                    blue = maxOf(max.blue, turn.blue)
                )
            }.power()
        }
    }

    private fun Game(input: String): Game {
        val parts = input.split(':', ';')
        val id = parts.first().filterDigits().toInt()
        val turns = parts.drop(1).map { part ->
            val map = part.split(',').associate { it.filterLetters() to it.filterDigits().toInt() }
            Turn(red = map["red"] ?: 0, green = map["green"] ?: 0, blue = map["blue"] ?: 0)
        }
        return Game(id = id, turns = turns)
    }

    data class Game(val id: Int, val turns: List<Turn>)
    data class Turn(val red: Int, val green: Int, val blue: Int) {
        fun isValid(maxRed: Int, maxGreen: Int, maxBlue: Int) = red <= maxRed && green <= maxGreen && blue <= maxBlue
        fun power() = blue * red * green
    }
}