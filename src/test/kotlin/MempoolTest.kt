import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FetchBlockIdTest {

    @Test
    fun testFetchFirstBlockId() = runBlocking{
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                })
            }
        }

        try {
            val firstBlockId = client.get("https://mempool.space/api/v1/blocks/730000").body<List<Block>>().first()
            assertEquals("0000000000000000000384f28cb3b9cf4377a39cfd6c29ae9466951de38c0529", firstBlockId.id)
        } catch (e: Exception) {
            println("Error fetching first block ID: $e")
        }
    }

    @Test
    fun testFetchTransactionId() = runBlocking{
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }

        try {
            val transactionIds = client.get("https://mempool.space/api/block/0000000000000000000384f28cb3b9cf4377a39cfd6c29ae9466951de38c0529/txids").body<Array<String>>().toList()[0]
            assertEquals("ce2ac5b6d807ae1368381a9ac5454d76ddb4b35742a87445aa145a78c5bc16d3", transactionIds)
        } catch (e: Exception) {
            println("Error fetching transaction ID: $e")
        }
    }
}