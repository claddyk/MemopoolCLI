import kotlinx.cli.*
@OptIn(ExperimentalCli::class)
fun main(args: Array<String>) {

    val resultWriter = ConsoleResultWriter()
    val apiClient = MempoolClient()
    val parser = ArgParser("kotlin-cli")
    parser.subcommands(
        FetchTxIdsCommand(resultWriter, apiClient),
        FetchBlockIdCommand(resultWriter, apiClient)
    )
    parser.parse(args)
}