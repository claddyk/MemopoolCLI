import kotlinx.cli.*

@OptIn(ExperimentalCli::class)
fun main(args: Array<String>) {
    val parser = ArgParser("kotlin-cli")
    val fetchBlockIdCommand = FetchBlockIdCommand()
    val fetchTxIdsCommand = FetchTxIdsCommand()
    parser.subcommands(fetchTxIdsCommand, fetchBlockIdCommand)
    parser.parse(args)
    println(fetchBlockIdCommand.execute())
    println(fetchTxIdsCommand.execute())
}
