import kotlinx.cli.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class FetchBlockIdCommandTest {
    @OptIn(ExperimentalCli::class)
    @Test
    fun `test execute fetchBlockIdCommand success`() {
        val resultWriter = DummyResultWriter()
        val command = FetchBlockIdCommand(resultWriter, DummyMempoolClient())
        val parser = ArgParser("test")
        parser.subcommands(command)
        parser.parse(arrayOf("fetchBlockId", "-s", "730000"))
        assertNull(resultWriter.lastError)
        assertEquals(DummyMempoolClient.blockId, resultWriter.lastResult)
    }
}
