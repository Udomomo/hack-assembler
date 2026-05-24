package instruction

/**
 * 読み込んだ命令1つを表すクラス。
 */
sealed interface Instruction {
    fun symbol(): String

    fun dest(): String?

    fun comp(): String

    fun jump(): String?
}
