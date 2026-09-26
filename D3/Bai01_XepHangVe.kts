//Nguyen Bao Trung - 25810047
fun main() {
    val tuoi = 25

    val loaiVe = if (tuoi < 12) {
        "Ve tre em"
    } else if (tuoi < 60) {
        "Ve nguoi lon"
    } else {
        "Ve cao tuong"
    }

    println("Loai ve: $loaiVe")
}
main()