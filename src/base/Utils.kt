package base

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * Shorthand to filter digits from a string.
 */
fun String.filterDigits(): String = filter { it.isDigit() }

/**
 * Shorthand to filter letters from a string.
 */
fun String.filterLetters(): String = filter { it.isLetter() }