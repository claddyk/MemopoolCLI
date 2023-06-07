import kotlinx.cli.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FetchBlockIdCommandTest {
    private lateinit var command: FetchBlockIdCommand

    @BeforeEach
    fun setup() {
        command = FetchBlockIdCommand(MempoolClient())
    }

    @OptIn(ExperimentalCli::class)
    @Test
    fun `test execute fetchBlockIdCommand success`() {
        val parser = ArgParser("test")
        parser.subcommands(command)
        parser.parse(arrayOf("fetchBlockId", "-s", "730000"))
        val expectedBlockId = "0000000000000000000384f28cb3b9cf4377a39cfd6c29ae9466951de38c0529"
        assertEquals(expectedBlockId, command.result)
    }
}
