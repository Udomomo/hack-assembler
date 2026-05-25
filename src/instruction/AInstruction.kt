package instruction

class AInstruction private constructor(
    private val symbol: String,
) : Instruction {
    companion object {
        fun from(line: String) = AInstruction(symbol = line.drop(1))
    }

    override fun symbol() = symbol

    override fun dest(): String = throw UnsupportedOperationException()

    override fun comp(): String = throw UnsupportedOperationException()

    override fun jump(): String = throw UnsupportedOperationException()
}
