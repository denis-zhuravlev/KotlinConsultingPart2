package com.otus.kotlinconsultingpart2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val c = C(A()) {a, b -> print("fdsfsd")}
        c.printSmthB()
        C.intNumber
        C.doSmth()
        C.Companion.doSmth()

        "testing" printWith 5

        println(c.toString())
        println(c.someField)
        c.someField = "fds"
        c.counter = 213

        val user = C.User()
        user.name = "first"
        user.name = "second"
    }

    infix fun String.printWith(intNum: Int) {
        println("$this and $intNum")
    }

    open class A : B {
        override fun printSmthB() {
            super.printSmthB()
            println("ffffff")
        }
    }

    interface B {
        fun printSmthB() {
            println("fdsfs")
        }
    }

    class C(b: B, val someAction: (a: Int, b: Int) -> Unit) : B by b {
        var someField = "abc"

        class User {
            var name: String by Delegates.observable("<no name>") {
                    prop, old, new ->
                println("$old -> $new")
            }
        }

        fun main() {
            val user = User()
            user.name = "first"
            user.name = "second"
        }

        var counter = 0 // Note: the initializer assigns the backing field directly
            set(value) {
                if (value >= 0) field = value
            }


        companion object {
            val intNumber = 23

            fun doSmth() {

            }
        }

        override fun printSmthB() {
            super.printSmthB()
            println("ccccccc")
            someAction(1, 2)
        }

        override fun toString(): String {
            return "C()"
        }


    }
}
