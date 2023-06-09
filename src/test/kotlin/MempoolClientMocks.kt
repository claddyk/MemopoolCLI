class DummyMempoolClient : IMempoolClient {
    override suspend fun fetchFirstBlockId(startHeight: Int): Result<String> = runCatching { blockId }

    override suspend fun fetchTransactionIds(blockId: String): Result<List<String>> = runCatching { txIds }

    companion object {
        const val blockId = "0000000000000000000384f28cb3b9cf4377a39cfd6c29ae9466951de38c0529"
        const val txId1 = "ce2ac5b6d807ae1368381a9ac5454d76ddb4b35742a87445aa145a78c5bc16d3"
        const val txId2 = "a86b3f93c1b2ea3f221159869d6f556cae1ba2622cc8c7eb71c7f4f64e0fbca4"
        val txIds = listOf(txId1, txId2)
    }
}

class FailingMempoolClient(private val failure: Throwable) : IMempoolClient {
    override suspend fun fetchFirstBlockId(startHeight: Int): Result<String> = throw failure
    override suspend fun fetchTransactionIds(blockId: String): Result<List<String>> = throw failure
}