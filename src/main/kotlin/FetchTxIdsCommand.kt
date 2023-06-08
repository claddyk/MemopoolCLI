import kotlinx.cli.*
import kotlinx.cli.ExperimentalCli
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCli::class)
class FetchTxIdsCommand(private val apiClient: MempoolClient) :
    Subcommand("fetchTxIds", "Fetch transaction IDs for a given block height") {
    private val startHeight by option(ArgType.Int, shortName = "s", description = "Start Height").required()

    var txIdsResult: List<String>? = null
    var error: String? = null

    override fun execute() = runBlocking {
        val blockIdResult = apiClient.fetchFirstBlockId(startHeight)
        if (blockIdResult.isSuccess) {
            val blockId = blockIdResult.getOrNull().toString()
            val txIdResult = apiClient.fetchTransactionIds(blockId)
            if (txIdResult.isSuccess) {
                txIdsResult = txIdResult.getOrNull()
                println(txIdsResult?.joinToString("\n"))
            } else {
                error = "Error fetching transaction IDs: ${txIdResult.exceptionOrNull()?.message}"
                println(error)
            }
        } else {
            error = "Error fetching block ID: ${blockIdResult.exceptionOrNull()?.message}"
            println(error)
        }
    }
}