package com.mauriciotogneri.momowars.utils

import kotlin.coroutines.experimental.*
import kotlin.js.Promise

// https://discuss.kotlinlang.org/t/async-await-on-the-client-javascript/2412/3

suspend fun <T> Promise<T>.await(): T =
        suspendCoroutine { cont ->
            then({ cont.resume(it) }, { cont.resumeWithException(it) })
        }

/*suspend fun test(email: String): Int =
        suspendCoroutine { cont ->

            Database.firestore
                    .collection("accounts")
                    .where("email", "==", email)
                    .get()
                    .then({ docs ->
                        cont.resume(123)
                    })
                    .catch({
                        cont.resumeWithException(Throwable())
                    })
        }*/

fun launch(block: suspend () -> Unit)
{
    block.startCoroutine(object : Continuation<Unit>
    {
        override val context: CoroutineContext get() = EmptyCoroutineContext

        override fun resume(value: Unit)
        {
        }

        override fun resumeWithException(exception: Throwable)
        {
            console.log("resumeWithException: " + exception.toString())

            throw exception
        }
    })
}