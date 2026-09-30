//Nguyen Bao Trung - 25810047
fun main() {
    val kiemTraDoDai: (String) -> Boolean = { password ->
        password.length >= 8
    }

    val pass1 = "12345"
    val pass2 = "kotlincode"
    val pass3 = "pass123"

    println("Mt khau: '$pass1' hop le (>= 8 ky tu): ${kiemTraDoDai(pass1)}")
    println("Mat khau: '$pass2' hop le (>= 8 ky tu): ${kiemTraDoDai(pass2)}")
    println("Mat khau: '$pass3' hop le (>= 8 ky tu): ${kiemTraDoDai(pass3)}")
}
main()