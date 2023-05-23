import kotlinx.cli.*
import java.net.URL
import java.net.HttpURLConnection

fun fetchBlocks(startHeight: Int): String {
    val url = URL("https://mempool.space/api/blocks/$startHeight")
    return try {
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        val responseCode = connection.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
            connection.inputStream.bufferedReader().use { it.readText() }
        } else {
            throw Exception("Failed to fetch blocks. Response code: $responseCode")
        }
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

fun main(args: Array<String>) {
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
