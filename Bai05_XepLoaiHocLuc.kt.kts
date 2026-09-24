//Buoi02Bai05 xep loai hoc luc, Nguyen Bao Trung - 25810047
fun xepLoai(){
    val diemTB = 8.5
    val xepLoai = when (diemTB) {
        in 9.0..10.0 -> "Xuat sac"
        in 8.0..<9.0 -> "Gioi"
        in 6.5..<8.0 -> "Kha"
        in 5.0..<6.5 -> "Trung binh"
        in 0.0..<5.0 -> "yeu"
        else -> "Diem khong hop le"
    }
    println("Diem trung binh: $diemTB")
    println("Xep loai: $xepLoai")
}
xepLoai()