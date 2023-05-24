import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required
import kotlinx.coroutines.runBlocking


fun main(args: Array<String>) = runBlocking {
    val parser = ArgParser("mempool-cli")
    val startHeight by parser.option(ArgType.Int, shortName = "s", description = "Start height").required()


    parser.parse(args)


    val mempoolSpace = MempoolSpace()
    val serialization = Serialization()


    try {
        val blockResponse = mempoolSpace.getBlocks(startHeight)
        val blockId = blockResponse.id
        val txIds = blockResponse.txids


        println("Block ID: $blockId")
        println("Transaction IDs:")
        txIds.forEach { txId -> println(txId) }
    } catch (e: Exception) {
        println("Error occurred: ${e.message}")
    }
}