# Advent of Code Kotlin Template

[Advent of Code][aoc] – an annual event in December since 2015.
Every year since then, with the first day of December, a programming puzzles contest is published every day for twenty-four days.
A set of Christmas-oriented challenges provides any input you have to use to answer using the language of your choice.
This is template with [Kotlin][kotlin] language to use for .

## Setup

After you create a new project based on the current template repository using the **Use this template** button, a bare minimal scaffold will appear in your GitHub account with the following structure:

```
.
├── README.md               README file
├── build.gradle.kts        Gradle configuration created with Kotlin DSL
├── settings.gradle.kts     Gradle project settings
├── gradle*                 Gradle wrapper files
└── src
    ├── base
        ├── Day.kt          Base class with printing utilities for each AoC day
        └── Utils.kt        A set of utility methods shared across your days
    └── day00               A template package for each AoC day
        ├── Day00.kt        A template file for each AoC day
        ├── input.txt       An empty file for the input data
        ├── test1.txt       An optional file for Part One test input data
        └── test2.txt       An optional file for Part Two test input data
```

> [!NOTE]
>
> All task input files (`src/**/*.txt`) are excluded from the repository with `.gitignore`

When each puzzle appears, copy the `day00` package and rename it to the current day.
Copy your `input` data to the `input.txt` file.
Each puzzle has an example input data and expected value, which you can copy to `test1.txt` or `test2.txt` for Part One and Part Two respectively.
In `DayXX.kt`, you can input the expected values for `test1Expected` and `test2Expected`.

```kotlin
object Day00 : Day<Long>("00") {
    override val test1Expected: Long = 0L
    override val test2Expected: Long = 0L
}
```

Provide your algorithm implementations in `part1` and `part2`.

```kotlin
    override fun part1(input: List<String>): Long {
        TODO()
    }

    override fun part2(input: List<String>): Long {
        TODO()
    }
```

## Running

To call the algorithm you're implementing, click the green Play button next to the `fun main()` definition.

```kotlin
    fun main() = Day00.run()
```

Using the base class will print the answers and run-time for each part.
Furthermore, if `test1Expected` and/or `test2Expected` is overridden, it will show the expected and actual values.

```
🎄🎁🎄🎁🎄️🎁🎄Day XX 🎄🎁🎄🎁🎄️🎁🎄️

**************   Test   **************
✅Part One: XXXX             ⏳0 ms
❌Part Two: XXXX             ⏳0 ms
❗️Expected: XXXX         

**************  Actual  **************
🌟Part One: XXXX             ⏳0 ms
🌟Part Two: XXXX             ⏳0 ms
```

Copy and paste your actual answers into the AoC page. 
If your answers are correct, you can override `part1Expected` and `part2Expected` to help with refactoring code.

```kotlin
object Day00 : Day<Long>("00") {
    override val part1Expected: Long = 0L
    override val part2Expected: Long = 0L
}
```

[actions-user]: https://github.com/actions-user
[aoc]: https://adventofcode.com
[kotlin]: https://kotlinlang.org
[slack]: https://surveys.jetbrains.com/s3/kotlin-slack-sign-up
[file:day]: src/base/Utils.kt
[file:utils]: src/base/Day.kt
