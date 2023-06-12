import kotlinx.cli.*
import kotlinx.cli.ExperimentalCli
import kotlinx.coroutines.runBlocking
import IMempoolClient

@OptIn(ExperimentalCli::class)
class FetchTxIdsCommand(
    private val resultWriter: IResultWriter, private val apiClient: IMempoolClient
) : Subcommand("fetchTxIds", "Fetch transaction IDs for a given block height") {
    private val startHeight by option(ArgType.Int, shortName = "s", description = "Start Height").required()
    override fun execute() = runBlocking {
        val blockIdResult = apiClient.fetchFirstBlockId(startHeight)
        if (blockIdResult.isSuccess) {
            val blockId = blockIdResult.getOrThrow()
            val txIdResult = apiClient.fetchTransactionIds(blockId)
            if (txIdResult.isSuccess) {
                val txIds = txIdResult.getOrThrow()
                txIds.forEach { resultWriter.writeSuccess(it) }
            } else {
                resultWriter.writeError("Error fetching transaction IDs: ${txIdResult.exceptionOrNull()?.message}")
            }
        }
    }
}
