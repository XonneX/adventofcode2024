import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        var safeReports = 0;
        for (line in input) {
            val levels = line.split(" ").toInt()

            val asc = levels[0] < levels[1]
            var safe = true
            for (i in 0..levels.size - 2) {
                val diff = abs(levels[i] - levels[i + 1])
                if (diff < 1 || diff > 3) {
                    safe = false
                }

                if (asc) {
                    if (levels[i] > levels[i + 1]) safe = false
                } else {
                    if (levels[i] < levels[i + 1]) safe = false
                }
            }

            if (safe) safeReports++
        }

        return safeReports
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    check(part1(listOf("7 6 4 2 1")) == 1)
    check(part1(listOf("1 2 7 8 9")) == 0)
    check(part1(listOf("1 3 6 7 9")) == 1)

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
