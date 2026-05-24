package instruction

class LInstruction private constructor(
    val symbol: String,
) : Instruction {
    companion object {
        fun from(line: String): LInstruction {
            val start = line.indexOf("(") + 1
            val end = line.indexOf(")")

            val symbol =
                if (start in 1..<end) {
                    line.substring(start, end)
                } else {
                    throw IllegalArgumentException("L_Instruction does not have () | currentLine=$line")
                }

            return LInstruction(symbol)
        }
    }

    override fun symbol() = symbol

    override fun dest(): String = throw UnsupportedOperationException()

    override fun comp(): String = throw UnsupportedOperationException()

    override fun jump(): String = throw UnsupportedOperationException()
}
