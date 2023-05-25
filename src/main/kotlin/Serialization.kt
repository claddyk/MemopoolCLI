import kotlinx.serialization.Serializable

@Serializable
data class BlockIds(val id: String)
data class TxIds(val txids: List<String>)
