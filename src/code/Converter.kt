package code

import instruction.AInstruction
import instruction.CInstruction
import instruction.Instruction

class Converter(
    val instruction: Instruction,
) {
    fun convert(): String? =
        when (instruction) {
            is AInstruction -> AInstructionConverter.convert(instruction)
            is CInstruction -> CInstructionConverter.convert(instruction)
            else -> null
        }
}
