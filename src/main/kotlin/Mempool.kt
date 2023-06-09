import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

interface IMempoolClient {
    suspend fun fetchFirstBlockId(startHeight: Int): Result<String>
    suspend fun fetchTransactionIds(blockId: String): Result<List<String>>
}

class MempoolClient: IMempoolClient {
    private val httpClient = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
    }

    override suspend fun fetchFirstBlockId(startHeight: Int): Result<String> {
        val response = httpClient.get("https://mempool.space/api/v1/blocks/$startHeight")
        return response.runCatching {
            body<List<Block>>().first().id
        }
    }

    override suspend fun fetchTransactionIds(blockId: String): Result<List<String>> {
        val response = httpClient.get("https://mempool.space/api/block/$blockId/txids")
        return response.runCatching {
            body<List<String>>()
        }
    }
}