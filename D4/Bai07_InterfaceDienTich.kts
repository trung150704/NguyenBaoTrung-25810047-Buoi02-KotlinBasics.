//Nguyen Bao Trung
import kotlin.math.PI

interface CoTheTinhDienTich {
    fun tinhDienTich(): Double
}

class HinhVuong(val canh: Double) : CoTheTinhDienTich {
    override fun tinhDienTich(): Double {
        return canh * canh
    }
}

class HinhTron(val banKinh: Double) : CoTheTinhDienTich {
    override fun tinhDienTich(): Double {
        return PI * banKinh * banKinh
    }
}

fun main() {
    val hinhVuong = HinhVuong(4.0)
    val hinhTron = HinhTron(3.0)

    println("Dien tich hinh vuong (canh = 4.0): ${hinhVuong.tinhDienTich()}")
    println("Dien tich hinh tron (ban kinh = 3.0): ${hinhTron.tinhDienTich()}")
}
main()