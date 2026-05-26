import code.Converter
import instruction.InstructionType
import parser.Parser
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 1) {
        println("Usage: java -jar Main.jar <path to .asm file>")
        exitProcess(1)
    }

    val inputPath = Paths.get(args[0])
    val inputFileName = inputPath.fileName.toString()

    if (inputFileName.split(".").size <= 1 || inputFileName.split(".").lastOrNull() != "asm") {
        println("Unsupported file type | only .asm file is supported.")
        exitProcess(1)
    }

    val outputFileName = inputFileName.substringBeforeLast(".") + ".hack"
    val outputPath = inputPath.resolveSibling(outputFileName)

    val reader = Files.newBufferedReader(inputPath)
    val writer = Files.newBufferedWriter(outputPath)

    var hasWrittenAnyLine = false
    reader.use {
        writer.use {
            val parser = Parser(reader)
            while (parser.hasMoreLines()) {
                parser.advance()
                val instructionType = parser.instructionType()
                printInstruction(parser, instructionType)

                val result = Converter(parser.currentInstruction).convert()
                if (result != null) {
                    // 最後の行に改行を入れないようにするため、「1行目以外は事前に改行を入れる」方式にしている。
                    if (hasWrittenAnyLine) {
                        writer.newLine()
                    }
                    writer.write(result)
                    hasWrittenAnyLine = true
                }
            }
        }
    }
}

/**
 * デバッグ用にParserの結果を出力する。
 */
private fun printInstruction(
    parser: Parser,
    instructionType: InstructionType,
) {
    when (instructionType) {
        InstructionType.A_INSTRUCTION -> {
            println("Type A | Symbol=${parser.symbol()}")
        }

        InstructionType.L_INSTRUCTION -> {
            println("Type L | Symbol=${parser.symbol()}")
        }

        InstructionType.C_INSTRUCTION -> {
            println("Type C | dest=${parser.dest()}, comp=${parser.comp()}, jump=${parser.jump()}")
        }

        InstructionType.EMPTY -> {}
    }
}
