//Nguyen Bao Trung - 25810047
fun binhPhuongFull(n: Int): Int {
    return n * n
}
fun binhPhuongShort(n: Int): Int = n * n

fun chuViHinhVuongFull(canh: Double): Double {
    return canh * 4
}
fun chuViHinhVuongShort(canh: Double): Double = canh * 4.0

fun laSoChanFull(n: Int): Boolean {
    return n % 2 == 0
}
fun laSoChanShort(n: Int): Boolean = n % 2 == 0

fun main() {
    println("1. Binh phuong so 5")
    println("Ban day du: ${binhPhuongFull(5)}")
    println("Ban rut gon: ${binhPhuongShort(5)}")

    println("2. Chu vi hinh vuong canh 4.5")
    println("Ban day du: ${chuViHinhVuongFull(4.5)}")
    println("Ban rut gon: ${chuViHinhVuongShort(4.5)}")

    println("3. Kiem tra so 8 co phai so chan")
    println("Ban day du: ${laSoChanFull(8)}")
    println("Ban rut gon: ${laSoChanShort(8)}")
}
main()