import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@Test
fun testFetchFirstBlockId() = runBlocking {
    val client = MempoolClient()
    val result = client.fetchFirstBlockId(730000)
    assertTrue(result.isSuccess)
    assertEquals("0000000000000000000384f28cb3b9cf4377a39cfd6c29ae9466951de38c0529",result.getOrThrow())
}