package instruction

class EmptyInstruction : Instruction {
    override fun symbol(): String = throw UnsupportedOperationException()

    override fun dest(): String = throw UnsupportedOperationException()

    override fun comp(): String = throw UnsupportedOperationException()

    override fun jump(): String = throw UnsupportedOperationException()
}
