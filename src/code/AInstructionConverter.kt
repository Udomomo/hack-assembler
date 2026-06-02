package code

import instruction.AInstruction
import symbol.SymbolTable

object AInstructionConverter {
    fun convert(instruction: AInstruction): String {
        val opCode = "0"
        val address =
            toAddress(instruction.symbol)
                ?: throw IllegalArgumentException("invalid symbol | aInstruction: $instruction")

        return "$opCode$address"
    }

    private fun toAddress(symbol: String): String? {
        val address =
            symbol.toIntOrNull()
                ?: SymbolTable.resolveSymbol(symbol)

        return address.toString(radix = 2).padStart(15, '0')
    }
}
