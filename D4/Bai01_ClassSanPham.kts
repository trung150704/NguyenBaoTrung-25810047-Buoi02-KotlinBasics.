//Nguyen Bao Trung - 25810047
class SanPham(
    val tenSanPham: String,
    val gia: Double,
    val soLuongTonKho: Int = 0
)

fun main() {
    val sp1 = SanPham("Laptop Dell", 150000.0, 10)

    val sp2 = SanPham(
        tenSanPham = "Chuột không dây",
        gia = 250000.0
    )

    println("Thong tin san pham 1")
    println("Ten san pham: ${sp1.tenSanPham}")
    println("Gia: ${sp1.gia} VNĐ")
    println("So luong ton kho: ${sp1.soLuongTonKho}")

    println("Thong tin san pham 2")
    println("Ten san pham: ${sp2.tenSanPham}")
    println("Gia: ${sp2.gia} VNĐ")
    println("So luong ton kho: ${sp2.soLuongTonKho}")
}
main()