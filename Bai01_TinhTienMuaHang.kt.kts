//Buoi02Bai01 tinh tien mua hang, Nguyen Bao Trung - 25810047
fun tinhTien(){
    val soLuong = 10
    val donGia = 150000.5
    val tienHang = soLuong.toDouble() * donGia
    val thue = tienHang * 0.08
    val tongTien = tienHang + thue
    println("So luong: $soLuong")
    println("Don gia: $donGia VND")
    println("Tien hang: $tienHang VND")
    println("Thue: $thue VND")
    println("Tong tien: $tongTien VND")
}
tinhTien()