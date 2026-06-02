import code.Converter
import instruction.EmptyInstruction
import parser.Parser
import symbol.SymbolTable
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

    val symbolReader = Files.newBufferedReader(inputPath)
    symbolReader.use {
        val parser = Parser(symbolReader)
        while (parser.hasMoreLines()) {
            parser.advance()
            SymbolTable.extractSymbol(parser.currentInstruction, parser.currentLineNumber)
        }
    }

    println("Symbols: $SymbolTable")

    val reader = Files.newBufferedReader(inputPath)
    val writer = Files.newBufferedWriter(outputPath)

    var hasWrittenAnyLine = false
    reader.use {
        writer.use {
            val parser = Parser(reader)
            while (parser.hasMoreLines()) {
                parser.advance()
                if (parser.currentInstruction !is EmptyInstruction) {
                    println(parser.currentInstruction)
                }

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
