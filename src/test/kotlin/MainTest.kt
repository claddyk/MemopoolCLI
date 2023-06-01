import kotlinx.cli.*
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class KotlinCliTest {

    @Test
    fun testFetchFirstBlockId() = runBlocking{
        val parser = ArgParser("kotlin-cli")
        val startHeight by parser.option(ArgType.Int, shortName = "s", description = "Start Height").required()

        parser.parse(arrayOf("-s", "730000"))

        try {
            val blockIds = fetchFirstBlockId(startHeight)
            assertEquals("0000000000000000000384f28cb3b9cf4377a39cfd6c29ae9466951de38c0529", blockIds)
        } catch (e: Exception) {
            println("Error fetching block ID: $e")
        }
    }

    @Test
    fun testFetchTransactionId() = runBlocking{
        val parser = ArgParser("kotlin-cli")
        val startHeight by parser.option(ArgType.Int, shortName = "s", description = "Start Height").required()

        parser.parse(arrayOf("-s", "1"))

        try {
            val firstTxId = fetchTransactionId("$startHeight")[0]
            assertEquals("ce2ac5b6d807ae1368381a9ac5454d76ddb4b35742a87445aa145a78c5bc16d3", firstTxId)
        } catch (e: Exception) {
            println("Error fetching transaction ID: $e")
        }
    }
}