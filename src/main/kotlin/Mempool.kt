import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.URL


class MempoolSpace {
    private val json = Json { ignoreUnknownKeys = true }


    suspend fun getBlocks(startHeight: Int): BlockResponse {
        val url = URL("https://mempool.space/api/v1/blocks/$startHeight")
        val blockJson = url.readText()
        return json.decodeFromString(BlockResponse.serializer(), blockJson)
    }
}


@Serializable
data class BlockResponse(val id: String, val txids: List<String>)