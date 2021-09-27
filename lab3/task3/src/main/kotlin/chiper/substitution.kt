package chiper

import java.util.*


internal fun getSubstitutionTable(key: Long): Map<Int, Int> {
    return (0..255)
        .shuffled(Random(key))
        .mapIndexed { index, item -> Pair(index, item) }
        .toMap()
}

internal fun getReversedSubstitutionMap(key: Long): Map<Int, Int> {
    return (0..255)
        .shuffled(Random(key))
        .mapIndexed { index, item -> Pair(item, index) }
        .toMap()
}
