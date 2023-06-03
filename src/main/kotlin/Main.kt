
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking() {
    val parser = ArgParser("kotlin-cli")
    val startHeight by parser.option(ArgType.Int, shortName = "s", description = "Start Height").required()

    parser.parse(args)

    val apiClient = MempoolClient()

    apiClient.fetchFirstBlockId(startHeight).fold(
        onSuccess = {blockIds ->
            apiClient.fetchTransactionIds(blockIds).fold(
                onSuccess = { txIds -> txIds.forEach { println(it) } },
                onFailure = { e-> println("error fetching transaction IDs: $e") }
            )
        },
        onFailure = { e -> println("error fetching block ID: $e") }
    )
}