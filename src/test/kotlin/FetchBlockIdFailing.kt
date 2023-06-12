import kotlinx.cli.*
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

@OptIn(ExperimentalCli::class)
class FetchBlockIdCommandFailingTest {

    @Test
    fun `test execute fetchBlockIdCommand with failing client`() {
        val resultWriter = DummyResultWriter()
        val failingClient = FailingMempoolClient(RuntimeException("Mocked failure"))
        val command = FetchBlockIdCommand(resultWriter, failingClient)
        val parser = ArgParser("test")
        parser.subcommands(command)
        parser.parse(arrayOf("fetchBlockId", "-s", "730000"))

        assertNotNull(resultWriter.lastError, "Mocked failure")
    }
}
