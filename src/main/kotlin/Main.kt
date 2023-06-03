import kotlinx.cli.*
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCli::class)
fun main(args: Array<String>) {
    val parser = ArgParser("kotlin-cli")
    parser.subcommands(
        FetchTxIdsCommand(),
        FetchBlockIdCommand()
    )
    parser.parse(args)
}

@OptIn(ExperimentalCli::class)
class FetchTxIdsCommand : Subcommand("fetchTxIds", "Fetch transaction IDs for a given block height") {
    private val startHeight by option(ArgType.Int, shortName = "s", description = "Start Height").required()

    override fun execute() = runBlocking{
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
}

@OptIn(ExperimentalCli::class)
class FetchBlockIdCommand : Subcommand("fetchBlockId", "Fetch block ID for a given block height") {
    private val startHeight by option(ArgType.Int, shortName = "s", description = "Start Height").required()

    override fun execute() = runBlocking{
        val apiClient = MempoolClient()

        apiClient.fetchFirstBlockId(startHeight).fold(
            onSuccess = {blockId ->
                println(blockId)
            },
            onFailure = { e -> println("error fetching block ID: $e") }
        )
    }
}