//Nguyen Bao Trung - 25810047
class KhachHang(
    var ho: String,
    var ten: String
) {
    var hoTen: String
        get() = "$ho $ten"
        set(value) {
            val index = value.indexOf(" ")
            if (index != -1) {
                ho = value.substring(0, index)
                ten = value.substring(index + 1)
            } else {
                ten = value
            }
        }
}

fun main() {
    val kh = KhachHang("Nguyen", "Trung")
    println("Ho ten ban dau: ${kh.hoTen}")

    kh.ten = "Dan"
    println("Sau khi doi ten thanh 'Dan': ${kh.hoTen}")

    kh.hoTen = "Le Thanh Tai"
    println("Sau khi gan hoTen = 'Le Thanh Tai':")
    println("Ho: ${kh.ho}")
    println("Ten: ${kh.ten}")
    println("Ho ten ghep lai: ${kh.hoTen}")
}
main()