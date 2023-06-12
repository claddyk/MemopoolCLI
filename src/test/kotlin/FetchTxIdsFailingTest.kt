import kotlinx.cli.*
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

@OptIn(ExperimentalCli::class)
class FetchTxIdsCommandFailingTest {

    @Test
    fun `test execute fetchTxIdsCommand with failing client`() {
        val resultWriter = DummyResultWriter()
        val failingClient = FailingMempoolClient(RuntimeException("Mocked failure"))
        val command = FetchTxIdsCommand(resultWriter, failingClient)
        val parser = ArgParser("test")
        parser.subcommands(command)
        parser.parse(arrayOf("fetchTxIds", "-s", "730000"))

        assertNotNull(resultWriter.lastError, "An error should have been written")
    }
}
