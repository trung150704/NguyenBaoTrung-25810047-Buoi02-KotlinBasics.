//Nguyen Bao Trung - 25810047
fun datBan(tenKhachHang: String, soLuongKhach: Int, loaiBan: String = "ban thuong") {
    println("Dat ban thanh cong | Khach hang: $tenKhachHang | So luong: $soLuongKhach nguoi | Loai ban: $loaiBan")
}

fun main() {
    datBan("Nguyen Bao Trung", 4)

    datBan("Le Quoc Viet", 2, "bàn VIP")

    datBan(soLuongKhach = 6, loaiBan = "ban san thuong", tenKhachHang = "Le Khai Dan")
}
main()