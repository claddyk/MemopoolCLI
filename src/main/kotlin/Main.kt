import kotlinx.cli.*

@OptIn(ExperimentalCli::class)
fun main(args: Array<String>) {
    val parser = ArgParser("kotlin-cli")
    parser.subcommands(
        FetchTxIdsCommand(),
        FetchBlockIdCommand()
    )
    parser.parse(args)
}