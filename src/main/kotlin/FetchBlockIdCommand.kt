import kotlinx.cli.ExperimentalCli
import kotlinx.cli.*
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCli::class)
class FetchBlockIdCommand(
    private val resultWriter: IResultWriter,
    private val apiClient: MempoolClient
) :
    Subcommand("fetchBlockId", "Fetch block ID for a given block height") {
    private var startHeight by option(ArgType.Int, shortName = "s", description = "Start Height").required()

    override fun execute() = runBlocking {
        val blockIdResult = apiClient.fetchFirstBlockId(startHeight)
        if (blockIdResult.isSuccess) {
            resultWriter.writeSuccess(blockIdResult.getOrThrow())
        } else {
            resultWriter.writeError("Error fetching block ID: ${blockIdResult.exceptionOrNull()?.message}")
        }
    }
}