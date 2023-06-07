import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FetchBlockIdCommandTest {
    private lateinit var command: FetchBlockIdCommand

    @BeforeEach
    fun setUp() {
        command = FetchBlockIdCommand()
    }

    private val startHeight = 730000

    @Test
    fun `should fetch block id`() = runBlocking {
        val expectedBlockId = "0000000000000000000384f28cb3b9cf4377a39cfd6c29ae9466951de38c0529"
        command.startHeight = startHeight
        val blockId = command.execute()

        assertEquals(expectedBlockId, blockId)
    }
}