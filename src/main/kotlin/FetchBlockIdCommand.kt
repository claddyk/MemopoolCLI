import kotlinx.cli.ExperimentalCli
import kotlinx.cli.*
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalCli::class)
class FetchBlockIdCommand(private val apiClient: MempoolClient) :
    Subcommand("fetchBlockId", "Fetch block ID for a given block height") {

    private val startHeight by option(ArgType.Int, shortName = "s", description = "Start Height").required()
    var result: String = ""

    override fun execute() = runBlocking {
        val blockIdResult = apiClient.fetchFirstBlockId(startHeight)
        result = if (blockIdResult.isSuccess) {
            blockIdResult.getOrNull().toString()
        } else {
            "Error fetching block ID: ${blockIdResult.exceptionOrNull()?.message}"
        }
        println(result)
    }
}