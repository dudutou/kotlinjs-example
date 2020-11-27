import react.dom.render
import kotlinx.browser.document
import kotlinx.browser.window

fun plus(v: dynamic) = v + 2

// dynamic is a special type in Kotlin/JS. It basically turns off Kotlin's type checker.
fun testDynamic() :String {
    val a: dynamic = "abc"
    val b: String = a
    fun firstChar(s: String) = s[0]

    var msg = (firstChar(a) == firstChar(b)).toString()
    msg += ", "
    msg += "${a.charCodeAt(0, "dummy argument")} == ${b[0].toInt()}"
    msg += ", "
    // A function call on a dynamic variable always returns a dynamic value, so it is possible to chain the calls.
    msg += (a.charAt(1).repeat(3)).toString()
    msg += ", "
    msg += "2 + 2 = ${plus(2)}"
    msg += ", "
    msg += "'2' + 2 = ${plus("2")}"

    return msg
}

// You can inline JavaScript code into your Kotlin code using the js("…") function.
fun testJsFunc1() {
    js("alert(\"alert from Kotlin!\")")
}

fun testJsFunc2() {
    val json = js("{}")
    json.name = "Jane"
    json.hobby = "movies"
    js("alert(JSON.stringify(json))")
}

external fun alert(msg: String)
fun testExternalDeclarations() {
    alert("Ni hao!")
}

fun main() {
    window.onload = {
        render(document.getElementById("root")) {
            child(Welcome::class) {
                attrs {
                    name = testDynamic()
                }
            }
        }
    }
    testJsFunc1()
    testJsFunc2()
    testExternalDeclarations()
}
