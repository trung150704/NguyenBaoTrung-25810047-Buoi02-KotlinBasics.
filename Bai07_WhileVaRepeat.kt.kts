//Buoi02Bai07 WhileVaRepeat, Nguyen Bao Trung - 25810047
fun WhileVaRepeat(){
    var count = 10
    while (count >= 1) {
        println(count)
        count--
    }
    println()
    repeat(5) { index ->
        println("Lan: ${index + 1}")
    }
}
WhileVaRepeat()