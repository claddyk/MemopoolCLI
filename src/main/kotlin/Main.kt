import kotlinx.cli.*

@OptIn(ExperimentalCli::class)
fun main(args: Array<String>) {
    val parser = ArgParser("kotlin-cli")
    val apiClient = MempoolClient()
    val fetchBlockIdCommand = FetchBlockIdCommand(apiClient)
    val fetchTxIdsCommand = FetchTxIdsCommand(apiClient)
    parser.subcommands(fetchTxIdsCommand, fetchBlockIdCommand)
    parser.parse(args)
    println(fetchBlockIdCommand.execute())
    println(fetchTxIdsCommand.execute())
}
