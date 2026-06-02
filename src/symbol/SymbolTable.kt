package symbol

import instruction.Instruction
import instruction.LInstruction

object SymbolTable {
    private val table =
        mutableMapOf(
            "R0" to 0,
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

    var nextVariableAddress = 15 + 1

    /**
     * 各行からsymbolを取り出し、シンボルテーブルに追加する。
     */
    fun extractSymbol(
        instruction: Instruction,
        currentLine: Int,
    ) {
        if (instruction !is LInstruction) return
        val symbol = instruction.symbol
        table[symbol] = currentLine + 1
    }

    /**
     * 各行のsymbolを解決する。
     * もしシンボルテーブルから見つからなければ、新しい変数とみなし記録も行う。
     */
    fun resolveSymbol(variable: String): Int {
        if (table.containsKey(variable)) {
            return table[variable]!!
        } else {
            table[variable] = nextVariableAddress
            nextVariableAddress++
            return table[variable]!!
        }
    }

    override fun toString() = table.toString()
}
