import kotlinx.cli.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class FetchTxIdsCommandTest {
    private lateinit var command: FetchTxIdsCommand

    @BeforeEach
    fun setup() {
        command = FetchTxIdsCommand()
    }

    @OptIn(ExperimentalCli::class)
    @Test
    fun `test execute fetchTxIdsCommand success`() {
        val parser = ArgParser("test")
        parser.subcommands(command)
        parser.parse(arrayOf("fetchTxIds", "-s", "730000"))
        val expectedFirstTxId = "ce2ac5b6d807ae1368381a9ac5454d76ddb4b35742a87445aa145a78c5bc16d3"
        assertEquals(expectedFirstTxId, command.txIdsResult?.get(0))
        assertNull(command.error)
    }
}
