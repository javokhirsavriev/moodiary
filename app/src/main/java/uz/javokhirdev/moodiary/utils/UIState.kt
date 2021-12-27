package uz.javokhirdev.moodiary.utils

sealed class UIState<out T> where T : Any? {
    object Ready : UIState<Nothing>()
    data class Loading(val isLoading: Boolean) : UIState<Nothing>()
    data class Success<T>(val data: T? = null) : UIState<T>()
    data class Failure(val message: String, val code: Int) : UIState<Nothing>()

    companion object {
        fun loading(isLoading: Boolean) = Loading(isLoading)
        fun <T> success(data: T? = null) = Success(data)
        fun failure(message: String, code: Int) = Failure(message, code)
    }
}

infix fun <T> UIState<T>.onLoading(onLoading: SingleBlock<UIState.Loading>): UIState<T> {
    return when (this) {
        is UIState.Loading -> {
            onLoading(this)
            this
        }
        else -> this
    }
}

infix fun <T> UIState<T>.onSuccess(onSuccess: SingleBlock<UIState.Success<T>>): UIState<T> {
    return when (this) {
        is UIState.Success -> {
            onSuccess(this)
            this
        }
        else -> this
    }
}

infix fun <T> UIState<T>.onFailure(onFailure: SingleBlock<UIState.Failure>): UIState<T> {
    return when (this) {
        is UIState.Failure -> {
            onFailure(this)
            this
        }
        else -> this
    }
}