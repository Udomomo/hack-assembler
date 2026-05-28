package instruction

class AInstruction private constructor(
    val symbol: String,
) : Instruction {
    companion object {
        fun from(line: String) = AInstruction(symbol = line.drop(1))
    }

    override fun toString() = "Type A | Symbol=$symbol"
}
