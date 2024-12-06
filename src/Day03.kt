fun main() {
    fun part1(input: List<String>): Int {
        val str = input.joinToString("")
        val regex = """mul\([0-9]{1,3},[0-9]{1,3}\)""".toRegex()

        val results = regex.findAll(str)

        var multiplicationResult = 0
        for (result in results) {
            val numbers = result.value
                .replace("mul(", "")
                .replace(")", "")
                .split(",")

            multiplicationResult += numbers[0].toInt() * numbers[1].toInt()
        }

        return multiplicationResult
    }

    fun part2(input: List<String>): Int {
        val str = input.joinToString("")
        val regex = """mul\([0-9]{1,3},[0-9]{1,3}\)|do\(\)|don't\(\)""".toRegex()

        val results = regex.findAll(str)

        var multiplicationResult = 0
        var does = true;
        for (result in results) {
            val numbers = result.value
                .replace("()", "")
                .replace("mul(", "")
                .replace(")", "")
                .split(",")

            if (numbers.size == 1) {
                does = numbers[0] == "do"
            } else if (does) {
                multiplicationResult += numbers[0].toInt() * numbers[1].toInt()
            }

        }

        return multiplicationResult
    }

    check(part1(listOf("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")) == 161)
    check(part2(listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")) == 48)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
