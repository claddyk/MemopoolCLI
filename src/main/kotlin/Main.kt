import kotlinx.cli.*
import java.net.URL

fun fetchBlocks(startHeight: Int): String {
    val url = URL("https://mempool.space/api/blocks/$startHeight")
    return url.readText()
}

fun main(args:Array<String>) {
    val parser = ArgParser("block-explorer")
    val startHeight by parser.option(
        ArgType.Int,
        shortName = "s",
        description = "Start height of the block"
    ).required()

    parser.parse(args)

    val blocksJson = fetchBlocks(startHeight)
    println(blocksJson)
}