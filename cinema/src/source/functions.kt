package source

const val PATH_ACCOUNTS: String = "backup/accounts.csv"
const val PATH_FILMS: String = "backup/films.txt"
const val PATH_HALL: String = "backup/hall.csv"
const val PASSWORD: String = "xorThiS123"
const val ROWS: Int = 5
const val COLUMNS: Int = 5
const val SEATS: Int = ROWS * COLUMNS

infix fun String.xor(that: String) = mapIndexed { index, c ->
    that[index].toInt().xor(c.toInt())
}.joinToString(separator = "") {
    it.toChar().toString()
}