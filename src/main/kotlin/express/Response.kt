package express

external class Response
{
    fun status(code: Int): Response

    fun send(value: Any): Response
}
