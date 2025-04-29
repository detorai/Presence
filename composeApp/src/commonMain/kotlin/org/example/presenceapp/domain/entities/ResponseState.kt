package org.example.presenceapp.domain.entities


sealed class Either<A, B> {
    class Left<A, B>(val value: A): Either<A, B>()
    class Right<A, B>(val value: B): Either<A, B>()
}