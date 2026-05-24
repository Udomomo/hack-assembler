import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

fun main(args: List<String>) {
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

    val outputFileName = inputFileName.replace("\\.([^.]+)$", ".hack")
    val outputPath = inputPath.resolveSibling(outputFileName)

    val reader = Files.newBufferedReader(inputPath)
    val writer = Files.newBufferedReader(outputPath)
    reader.use {
        writer.use {
        }
    }
}
