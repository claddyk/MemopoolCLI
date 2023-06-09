import kotlinx.cli.ExperimentalCli
import kotlinx.cli.*
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCli::class)
class FetchBlockIdCommand(private val apiClient: MempoolClient = MempoolClient()) : Subcommand("fetchBlockId", "Fetch block ID for a given block height") {
    var startHeight by option(ArgType.Int, shortName = "s", description = "Start Height").required()

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