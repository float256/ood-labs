package chiper

import java.util.*
import kotlin.math.abs


internal fun getSubstitutionTable(key: Long): Map<Int, Int> {
    return (Byte.MIN_VALUE..Byte.MAX_VALUE)
        .shuffled(Random(key))
        .mapIndexed { index, item -> Pair(index + Byte.MIN_VALUE, item) }
        .toMap()
}

internal fun getReversedSubstitutionMap(key: Long): Map<Int, Int> {
    return (0..255)
        .shuffled(Random(key))
        .mapIndexed { index, item -> Pair(item, index) }
        .toMap()
}
