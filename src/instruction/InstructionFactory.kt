package instruction

object InstructionFactory {
    fun from(line: String): Instruction =
        when (checkType(line)) {
            InstructionType.EMPTY -> EmptyInstruction()
            InstructionType.A_INSTRUCTION -> AInstruction.from(line)
            InstructionType.C_INSTRUCTION -> CInstruction.from(line)
            InstructionType.L_INSTRUCTION -> LInstruction.from(line)
        }

    private fun checkType(line: String): InstructionType =
        if (line.isBlank() || line.startsWith("//")) {
            InstructionType.EMPTY
        } else if (line.startsWith("@")) {
            InstructionType.A_INSTRUCTION
        } else if (line.startsWith("(") && line.endsWith(")")) {
            InstructionType.L_INSTRUCTION
        } else {
            InstructionType.C_INSTRUCTION
        }
}
