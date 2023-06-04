import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MempoolClientTest {
    private val client = MempoolClient()

    @Test
    fun testFetchFirstBlockId() = runBlocking {
        val result = client.fetchFirstBlockId(730000)
        assertTrue(result.isSuccess)
        assertEquals("0000000000000000000384f28cb3b9cf4377a39cfd6c29ae9466951de38c0529",result.getOrThrow())
    }

    @Test
    fun testFetchTransactionId() = runBlocking {
        val result = client.fetchTransactionIds("0000000000000000000384f28cb3b9cf4377a39cfd6c29ae9466951de38c0529")
        assertTrue(result.isSuccess)
        assertEquals("ce2ac5b6d807ae1368381a9ac5454d76ddb4b35742a87445aa145a78c5bc16d3", result.getOrThrow()[0])
    }
}
