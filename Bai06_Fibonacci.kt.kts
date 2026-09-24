//Buoi02Bai06 Fibonacci, Nguyen Bao Trung - 25810047
fun Fibonacci(){
    var f0 = 0
    var f1 = 1
    var stt = 0

    for (i in 0..100) {
        if (f0 >= 100) break

        println("Vi tri: $stt: $f0")

        val fn = f0 + f1
        f0 = f1
        f1 = fn

        stt++
    }
}
Fibonacci()