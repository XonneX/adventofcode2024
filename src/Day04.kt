fun main() {

    fun search1(chars: Array<CharArray>, row: Int, col: Int, word: String): Int {
        val maxRow = chars.size
        val maxCol = chars[0].size

        if (chars[row][col] != word[0]) {
            return 0
        }

        // x and y are used to set the direction in which
        // word needs to be searched.
        val x = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val y = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

        val len = word.length

        var found = 0

        // Search in 8 directions
        for (dir in 0..7) {
            var k = 1
            var currX = row + x[dir]
            var currY = col + y[dir]

            while (k < len) {
                if (currX >= maxRow || currX < 0 || currY >= maxCol || currY < 0) break

                if (chars[currX][currY] != word[k]) break

                currX += x[dir]
                currY += y[dir]
                k++
            }

            if (k == len) found++
        }

        return found
    }

    fun part1(input: List<String>): Int {
        val chars = input.map { it.toCharArray() }.toTypedArray()

        val word = "XMAS"

        val maxRow = chars.size - 1
        val maxCol = chars[0].size - 1


        var found = 0

        for (row in 0..maxRow) {
            for (col in 0..maxCol) {
                found += search1(chars, row, col, word)
            }
        }

        return found
    }

    fun search2(chars: Array<CharArray>, row: Int, col: Int): Boolean {
        val maxRowIndex = chars.size - 1
        val maxColIndex = chars[0].size - 1

        if (chars[row][col] != 'A') {
            return false
        }

        if (row < 1 || row > maxRowIndex - 1 || col < 1 || col > maxColIndex - 1) {
            return false
        }

        val tl = chars[row - 1][col - 1]
        val tr = chars[row - 1][col + 1]
        val bl = chars[row + 1][col - 1]
        val br = chars[row + 1][col + 1]

        if ((tl == 'M' && br == 'S') || (tl == 'S' && br == 'M')) {
            if ((tr == 'M' && bl == 'S') || (tr == 'S' && bl == 'M')) {
                return true
            }
        }

        return false
    }

    fun part2(input: List<String>): Int {
        val chars = input.map { it.toCharArray() }.toTypedArray()

        val maxRow = chars.size - 1
        val maxCol = chars[0].size - 1


        var found = 0

        for (row in 0..maxRow) {
            for (col in 0..maxCol) {
                if (search2(chars, row, col)) {
                    found++
                }
            }
        }

        return found
    }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)

    val testInput2 = readInput("Day04_test2")
    check(part2(testInput2) == 9)


    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
