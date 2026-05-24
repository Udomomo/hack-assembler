package instruction

/**
 * 入力されたアセンブリ言語の、各行の命令の種別。
 */
enum class InstructionType {
    /**
     * 行を読み込む前の初期状態や、空白行の場合。
     */
    EMPTY,
    A_INSTRUCTION,
    C_INSTRUCTION,
    L_INSTRUCTION,
}
