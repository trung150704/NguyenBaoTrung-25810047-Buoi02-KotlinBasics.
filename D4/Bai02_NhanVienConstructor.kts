// Nguyen Bao Trung -25810047
class NhanVien(
    maNhanVien: String,
    val ten: String,
    var luongThang: Double
) {
    constructor(ten: String) : this("TAM", ten, 0.0)
}

fun main() {
    val nv1 = NhanVien("NV01", "Le Khai Dan", 150000.0)

    val nv2 = NhanVien("Nguyen Bao Trung")

    println("Nhan vien 1 (Constructor chinh)")
    println("Ten: ${nv1.ten}, Luong thang: ${nv1.luongThang}")

    println("Nhan vien 2 (Constructor phu)")
    println("Ten: ${nv2.ten}, Luong thang: ${nv2.luongThang}")
}
main()