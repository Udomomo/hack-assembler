package parser

import instruction.AInstruction
import instruction.CInstruction
import instruction.EmptyInstruction
import instruction.InstructionFactory
import instruction.InstructionType
import instruction.LInstruction
import java.io.EOFException
import java.io.Reader

/**
 * Hackアセンブリ言語の各行をパースし、バイナリコードに変換する。
 * 構文エラーに対するバリデーション・対応は考慮していない。
 */
class Parser(
    reader: Reader,
) {
    private val bufferedReader = reader.buffered()

    var currentInstruction = InstructionFactory.from("")
        private set

    // 次の行の存在確認のため先読みしておく。
    private var nextLine: String? = bufferedReader.readLine()?.trim()

    fun hasMoreLines() = nextLine != null

    /**
     * 次の行に進む。1行目を読むときもこのメソッドを呼ぶ必要がある。
     */
    fun advance() {
        if (nextLine == null) {
            throw EOFException("File has no more lines.")
        }

        currentInstruction = InstructionFactory.from(nextLine!!)
        nextLine = bufferedReader.readLine()?.trim()
    }

    fun instructionType() =
        when (currentInstruction) {
            is EmptyInstruction -> InstructionType.EMPTY
            is AInstruction -> InstructionType.A_INSTRUCTION
            is LInstruction -> InstructionType.L_INSTRUCTION
            is CInstruction -> InstructionType.C_INSTRUCTION
        }

    /**
     * 命令に含まれるシンボルを取り出す。A命令, L命令の場合のみ利用可。
     */
    fun symbol(): String = currentInstruction.symbol()

    /**
     * 命令のdest部分を取り出す。dest部分がない場合はnullを返す。
     * C命令の場合のみ利用可。
     */
    fun dest(): String? = currentInstruction.dest()

    /**
     * 命令のcomp部分を取り出す。C命令の場合のみ利用可。
     */
    fun comp(): String = currentInstruction.comp()

    /**
     * 命令のjump部分を取り出す。jump部分がない場合はnullを返す。
     * C命令の場合のみ利用可。
     */
    fun jump(): String? = currentInstruction.jump()
}
