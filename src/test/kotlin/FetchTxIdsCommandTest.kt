import kotlinx.cli.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class FetchTxIdsCommandTest {
    @OptIn(ExperimentalCli::class)
    @Test
    fun `test execute fetchTxIdsCommand success`() {
        val resultWriter = DummyResultWriter()
        val apiClient = DummyMempoolClient()
        val command = FetchTxIdsCommand(resultWriter, apiClient)
        val parser = ArgParser("test")
        parser.subcommands(command)
        parser.parse(arrayOf("fetchTxIds", "-s", "730000"))
        assertNull(resultWriter.lastError)
        assertEquals(DummyMempoolClient.txIds, resultWriter.lastResult?.split("\n"))
    }
}