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

    // シンボルの解決に必要なので行番号を記録する。空行・コメント行・シンボルの行はカウントしない。
    var currentLineNumber = -1
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

        if (currentInstruction is AInstruction || currentInstruction is CInstruction) {
            currentLineNumber++
        }
        nextLine = bufferedReader.readLine()?.trim()
    }
}
