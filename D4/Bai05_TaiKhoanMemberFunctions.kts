//Nguyen Bao Trung - 25810047

class TaiKhoanNganHang(
    val soTaiKhoan: String,
    soDuBanDau: Double
) {
    var soDu: Double = soDuBanDau

    init {
        if (soDuBanDau < 0) {
            println("So du khong hop le")
            soDu = 0.0
        }
    }

    fun napTien(soTien: Double) {
        if (soTien > 0) {
            soDu += soTien
            println("Nap thanh cong: +$soTien VNĐ")
        } else {
            println("So tien nap phai lon hon 0")
        }
    }

    fun rutTien(soTien: Double): Boolean {
        return if (soTien > 0 && soDu >= soTien) {
            soDu -= soTien
            println("Rut thanh cong: -$soTien VNĐ")
            true
        } else {
            println("Rut that bai: So du khong du hoac so tien khong hop le")
            false
        }
    }
}

fun main() {
    val tk = TaiKhoanNganHang("101202303", 1000000.0)
    println("So du ban dau: ${tk.soDu} VNĐ\n")

    tk.napTien(500000.0)
    println("So du hien tai: ${tk.soDu} VNĐ\n")

    val ketQua1 = tk.rutTien(800000.0)
    println("Ket qua rut: $ketQua1 | So du hien tai: ${tk.soDu} VNĐ\n")

    val ketQua2 = tk.rutTien(1000000.0)
    println("Ket qua rut: $ketQua2 | So du hien tai: ${tk.soDu} VNĐ")
}
main()
