//Nguyen Bao Trung - 25810047
class TaiKhoanNganHang(
    soTaiKhoan: String,
    soDuBanDau: Double
) {
    var soDu: Double = soDuBanDau

    init {
        if (soDuBanDau < 0) {
            println("So du khong hop le (So tai khoan: $soTaiKhoan)")
        } else {
            println("Tao tai khoan thanh cpng! So tai khoan: $soTaiKhoan, So du ban dau: $soDuBanDau")
        }
    }
}

fun main() {
    println("Khoi tao tai khoan 1 (Hop le)")
    val tk1 = TaiKhoanNganHang("123456789", 500000.0)

    println("Khoi tao tai khoan 2 (Khong hop le) ---")
    val tk2 = TaiKhoanNganHang("987654321", -100000.0)
}
main()