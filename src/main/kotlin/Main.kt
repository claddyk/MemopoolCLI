import kotlinx.cli.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.net.URL

data class BlockInfo(val blockId: String, val txIds: List<String>)

fun main(args: Array<String>) = runBlocking {
    val parser = ArgParser("mempool-explorer")
    val startHeight by parser.option(ArgType.Int, shortName = "s", description = "Start block height").required()

    parser.parse(args)

    val blockInfo = fetchBlockInfo(startHeight)
    println("Block ID: ${blockInfo.blockId}")
    println("Transaction IDs:")
    blockInfo.txIds.forEach { println(it) }
}

private suspend fun fetchBlockInfo(startHeight: Int): BlockInfo = withContext(Dispatchers.IO) {
    val url = URL("https://mempool.space/api/v1/blocks/$startHeight")
    val connection = url.openConnection()
    connection.connectTimeout = 5000
    connection.readTimeout = 5000
    val json = connection.getInputStream().bufferedReader().use { it.readText() }
    val blockId = json.substringAfter("hash\":\"").substringBefore("\",")
    val txIds = json.substringAfter("txids\":[\"").substringBefore("\"],").split("\",\"")
    BlockInfo(blockId, txIds)
}

