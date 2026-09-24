//Buoi02Bai04 tinh BMI, Nguyen Bao Trung - 25810047
fun BMI(){
    val canNang = 50.0
    val chieuCao = 1.70
    val bmi = canNang / (chieuCao * 2)
    val phanLoai = if (bmi < 18.5){
        "Gay"
    }else if (bmi < 25.0){
        "Binh thuong"
    }else if(bmi < 30.0) {
        "Thua can"
    }else{
        "Beo phi"
    }
    println("Can nang: $canNang")
    println("Chieu cao: $chieuCao")
    println("BMI: $bmi")
    println("Phan loai: $phanLoai")
}
BMI()