//Nguyen Bao Trung
open class DongVat(val ten: String) {
    open fun keu(): String {
        return "..."
    }
}

class Cho(ten: String) : DongVat(ten) {
    override fun keu(): String {
        return "Gau gau!"
    }
}

class Meo(ten: String) : DongVat(ten) {
    override fun keu(): String {
        return "Meo meo!"
    }
}

fun main() {
    val danhSachDongVat: List<DongVat> = listOf(
        Cho("Cau Vang"),
        Meo("Meo Meww"),
        Cho("Cho Tay Tang"),
        Meo("Meo Rung Chau Phi")
    )

    println("Tieng keu cua cac con vat")
    for (dongVat in danhSachDongVat) {
        println("${dongVat.ten}: ${dongVat.keu()}")
    }
}
main()