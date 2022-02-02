package com.example.osagocalculation.data

import com.example.osagocalculation.domain.Repository
import com.example.osagocalculation.domain.entities.Coefficients

class RepositoryImpl : Repository {

    override fun getData(): List<Coefficients> {
        val coefficientsList = listOf(
            Coefficients.Coefficient(
                "БТ (базовый тариф)",
                "Устанавливает страховая компания",
                "2 754 - 4 432 Р"
            ),
            Coefficients.Coefficient(
                "КМ (коэфф. мощности)",
                "Чем мощнее автомобиль, тем дороже страховой полис",
                "0,6 - 1,6"
            ),
            Coefficients.Coefficient(
                "КТ (территориальный коэфф.)",
                "Определяется по прописке собственника автомобиля",
                "0,64 - 1,99"
            ),
            Coefficients.Coefficient(
                "КБМ (коэфф. безаварийности)",
                "Учитывается только самый высокий коэффициент из всех водителей",
                "0,5 - 2,45"
            ),
            Coefficients.Coefficient(
                "КВС (коэфф. возраст/стаж)",
                "Чем больше возраст и стаж у вписанного в полис водителя, тем дешевле будет полис",
                "0,90 - 1,93"
            ),
            Coefficients.Coefficient(
                "КО (коэфф. ограничений)",
                "Полис с ограниченным списком водителей будет стоить дешевле",
                "1 или 1,99"
            )
        )
        return arrayListOf(
            Coefficients.Header(coefficientsList),
            Coefficients.Coefficient(
                "БТ (базовый тариф)",
                "Устанавливает страховая компания",
                "2 754 - 4 432 Р"
            ),
            Coefficients.Coefficient(
                "КМ (коэфф. мощности)",
                "Чем мощнее автомобиль, тем дороже страховой полис",
                "0,6 - 1,6"
            ),
            Coefficients.Coefficient(
                "КТ (территориальный коэфф.)",
                "Определяется по прописке собственника автомобиля",
                "0,64 - 1,99"
            ),
            Coefficients.Coefficient(
                "КБМ (коэфф. безаварийности)",
                "Учитывается только самый высокий коэффициент из всех водителей",
                "0,5 - 2,45"
            ),
            Coefficients.Coefficient(
                "КВС (коэфф. возраст/стаж)",
                "Чем больше возраст и стаж у вписанного в полис водителя, тем дешевле будет полис",
                "0,90 - 1,93"
            ),
            Coefficients.Coefficient(
                "КО (коэфф. ограничений)",
                "Полис с ограниченным списком водителей будет стоить дешевле",
                "1 или 1,99"
            )
        )
    }

    override fun getFormData(): List<String> {
        return listOf(
            "Город регистрации собственника",
            "Мощность автомобиля",
            "Сколько водителей",
            "Возраст младшего из водителей",
            "Минимальный стаж водителей",
            "Сколько лет не было аварий"
        )
    }

}
