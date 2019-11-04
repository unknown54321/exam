package domini_exam.dominiGeiger_exam.dto


open class WrappedResponse<T>(

        // The HTTP status code of the response
        var code: Int? = null,

        // The wrapped payload
        var data: T? = null,

        // Error message in case where was an error
        var message: String? = null,

        // String representing either success, user error ('error') or server failure ('fail)
        var status: ResponseStatus? = null
) {

    fun validated() : WrappedResponse<T>{

        val c : Int = code ?: throw IllegalStateException("Missing HTTP code")

        if(c !in 100..599){
            throw  IllegalStateException("Invalid HTTP code: $code")
        }

        if(status == null){
            status = when (c) {
                in 100..399 -> ResponseStatus.SUCCESS
                in 400..499 -> ResponseStatus.ERROR
                in 500..599 -> ResponseStatus.FAIL
                else -> throw  IllegalStateException("Invalid HTTP code: $code")
            }
        } else {
            val wrongSuccess =  (status ==  ResponseStatus.SUCCESS && c !in 100..399)
            val wrongError =  (status ==  ResponseStatus.ERROR && c !in 400..499)
            val wrongFail =  (status ==  ResponseStatus.FAIL && c !in 500..599)

            val wrong = wrongSuccess || wrongError || wrongFail
            if(wrong){
                throw IllegalArgumentException("Status $status is not correct for HTTP code $c")
            }
        }

        if(status != ResponseStatus.SUCCESS && message == null){
            throw IllegalArgumentException("Failed response, but with no describing 'message' for it")
        }

        return this
    }

    enum class ResponseStatus {
        SUCCESS, FAIL, ERROR
    }
}




