package com.jagaldol.behind.fitness._core.utils

data class CursorRequest(
    val key: Long?,
    val size: Int?
) {
    companion object {
        const val NONE_KEY = -1L
        const val DEFAULT_SIZE = 20
    }

    fun next(key: Long, size: Int? = null) = size?.let { CursorRequest(key, size) } ?: CursorRequest(key, this.size)
}
