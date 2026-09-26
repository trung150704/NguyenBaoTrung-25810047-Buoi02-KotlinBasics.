//Nguyen Bao Trung - 25810047
fun dinhDangDiaChi(
    soNha: String,
    duong: String,
    phuong: String = "Phuong Linh Xuan",
    quan: String = "Quan 2",
    thanhPho: String = "TP. Ho Chi Minh")
:String{
    return "Dia chi: $soNha $duong - $phuong - $quan - $thanhPho"
}
fun main(){
    val diaChi1 = dinhDangDiaChi("582","Quang Trung",quan = "Quan 2", thanhPho = "TP. Ho Chi Minh")
    val diaChi2 = dinhDangDiaChi("291","Kha Van Can",phuong = "Phuong Linh Xuan", thanhPho = "TP. Ho Chi Minh",quan = "Quan 2")
    println(diaChi1)
    println(diaChi2)
}
main()
