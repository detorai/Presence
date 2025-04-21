package org.example.presenceapp.domain.entities

sealed class ResponseState {
    class Error(val error: String): ResponseState()
    class Success<T>(val data: T): ResponseState()
}

sealed class Either<A, B> {
    class Left<A, B>(val value: A): Either<A, B>()
    class Right<A, B>(val value: B): Either<A, B>()
}