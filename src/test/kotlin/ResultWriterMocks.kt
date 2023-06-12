class DummyResultWriter : IResultWriter {
    var lastResult: String? = null
    var lastError: String? = null

    override fun writeSuccess(result: String) {
        lastResult = result
    }

    override fun writeError(message: String) {
        lastError = message
    }
}