fun main() {
    fun part1(input: List<String>): Int {
        val (orderingRulesLines, pageLines) = input.joinToString("\n").split("\n\n").map { it.split("\n") }
        val orderingRules = orderingRulesLines.groupBy(
            { it.split("|")[0] },
            { it.split("|")[1] },
        )
        val pagesLines = pageLines.map { it.split(",") }

        val validMiddlePages = arrayListOf<String>()

        for (pages in pagesLines) {
            val visitedPages = arrayListOf<String>()
            var valid = true

            for (page in pages) {
                val mustBeBeforeThese = orderingRules[page]

                if (mustBeBeforeThese != null) {
                    for (mustBeBeforeThis in mustBeBeforeThese) {
                        if (visitedPages.contains(mustBeBeforeThis)) {
                            valid = false
                            break
                        }
                    }
                }

                if (!valid) {
                    break
                }

                visitedPages+= page
            }

            if (valid) {
                validMiddlePages += pages[pages.size / 2]
            }
        }

        return validMiddlePages.toInt().sum()
    }

    fun part2(input: List<String>): Int {
        val (orderingRulesLines, pageLines) = input.joinToString("\n").split("\n\n").map { it.split("\n") }
        val orderingRules = orderingRulesLines.groupBy(
            { it.split("|")[0] },
            { it.split("|")[1] },
        )
        val pagesLines = pageLines.map { it.split(",") }

        val invalidMiddlePages = arrayListOf<String>()

        for (pages in pagesLines) {
            val visitedPages = arrayListOf<String>()
            var valid = true

            for (page in pages) {
                val mustBeBeforeThese = orderingRules[page]

                if (mustBeBeforeThese != null) {
                    for (mustBeBeforeThis in mustBeBeforeThese) {
                        if (visitedPages.contains(mustBeBeforeThis)) {
                            valid = false
                            break
                        }
                    }
                }

                if (!valid) {
                    break
                }

                visitedPages+= page
            }

            if (valid) {
                continue
            }

            val cmp = object: Comparator<String> {
                override fun compare(o1: String, o2: String): Int {
                    val o1MustBeBefore = orderingRules[o1]
                    if (o1MustBeBefore != null) {
                        if (o1MustBeBefore.contains(o2)) {
                            return -1
                        }
                    }

                    val o2MustBeBefore = orderingRules[o2]
                    if (o2MustBeBefore != null) {
                        if (o2MustBeBefore.contains(o1)) {
                            return 1
                        }
                    }

                    return 0
                }

            }

            val sortedPages = pages.sortedWith(cmp)

             invalidMiddlePages += sortedPages[sortedPages.size / 2]
        }

        return invalidMiddlePages.toInt().sum()
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == 143)

    check(part2(testInput) == 123)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
