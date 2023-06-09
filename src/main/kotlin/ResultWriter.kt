interface IResultWriter {
    fun writeSuccess(result: String)
    fun writeError(message: String)
}

class ConsoleResultWriter : IResultWriter {
    override fun writeSuccess(result: String) = println(result)
    override fun writeError(message: String) = println(message)
}
