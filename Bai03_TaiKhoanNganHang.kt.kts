//Buoi02Bai03 tai khoan ngan hang, Nguyen Bao Trung - 25810047
fun tkNganHang(){
    val soDuBanDau = 5000000.0
    var soDuHienTai = soDuBanDau
    println("So du ban dau: $soDuBanDau")
    val tienGui = 2000000.0
    soDuHienTai += tienGui
    println("So du sau khi gui: $soDuHienTai")
    val rutTien = 1500000.0
    soDuHienTai -= rutTien
    println("So du sau khi rut: $soDuHienTai")
    println("So du ban dau la: $soDuBanDau")
    //chon val vi no la gia tri khong duoc phep thay doi
    //chon var vi noi duoc phep thay doi gia tri
}
tkNganHang()