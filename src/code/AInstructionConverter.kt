package code

import instruction.AInstruction

object AInstructionConverter {
    fun convert(instruction: AInstruction): String {
        val opCode = "0"
        val address =
            toAddress(instruction.symbol())
                ?: throw IllegalArgumentException("invalid symbol | aInstruction: $instruction")

        return "$opCode$address"
    }

    private fun toAddress(symbol: String): String? {
        val address =
            symbol.toIntOrNull()
                ?: reservedSymbols[symbol]

        return address?.toString(radix = 2)
    }

    private val reservedSymbols: Map<String, Int> =
        mapOf(
            "R1" to 1,
            "R2" to 2,
            "R3" to 3,
            "R4" to 4,
            "R5" to 5,
            "R6" to 6,
            "R7" to 7,
            "R8" to 8,
            "R9" to 9,
            "R10" to 10,
            "R11" to 11,
            "R12" to 12,
            "R13" to 13,
            "R14" to 14,
            "R15" to 15,
        )
}
