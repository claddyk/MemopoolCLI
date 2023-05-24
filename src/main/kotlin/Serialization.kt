import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json


class Serialization {
    private val json = Json { ignoreUnknownKeys = true }


    fun parseBlockResponse(json: String): BlockResponse {
        return json.decodeFromString(BlockResponse.serializer())
    }


    private fun String.decodeFromString(serializer: KSerializer<BlockResponse>): BlockResponse {
        return json.decodeFromString(serializer, this)
    }
}
