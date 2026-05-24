package instruction

class CInstruction private constructor(
    private val dest: String?,
    private val comp: String,
    private val jump: String?,
) : Instruction {
    companion object {
        fun from(line: String): CInstruction {
            val trimmed = line.filterNot { it.isWhitespace() }

            val eqIndex = trimmed.indexOf('=')
            val semiIndex = trimmed.indexOf(';')

            // ;よりも=が後にある場合は不正とする
            if (eqIndex >= 0 && semiIndex >= 0 && eqIndex > semiIndex) {
                throw IllegalArgumentException("Invalid C_Instruction | currentLine=$trimmed")
            }

            val dest = if (eqIndex >= 0) trimmed.take(eqIndex) else null
            val jump = if (semiIndex >= 0) trimmed.substring(semiIndex + 1) else null

            val compStartIndex = if (eqIndex >= 0) eqIndex + 1 else 0
            val compEndIndex = if (semiIndex >= 0) semiIndex else trimmed.length
            val comp = trimmed.substring(compStartIndex, compEndIndex)

            return CInstruction(dest, comp, jump)
        }
    }

    override fun dest(): String? = dest

    override fun comp(): String = comp

    override fun jump(): String? = jump

    override fun symbol(): String = throw UnsupportedOperationException()
}
