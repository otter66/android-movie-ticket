package woowacourse.movie.domain.seat

data class Seat(
    val row: Row,
    val column: Int,
    val seatType: SeatType
) {
    companion object {
        fun getSeatType(row: Char): SeatType =
            enumValues<SeatType>().find { seatType -> seatType.targetRows.contains(Row(row)) }!!

        fun from(row: Char, column: Int): Seat = Seat(Row(row), column, getSeatType(row))
    }
}
