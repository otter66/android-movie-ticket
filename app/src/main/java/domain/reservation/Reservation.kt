package domain.reservation

import domain.discount.Discount
import domain.movie.Movie
import domain.payment.PaymentAmount
import domain.payment.PaymentType
import java.io.Serializable
import java.time.LocalDateTime

data class Reservation(
    val movie: Movie,
    val screeningDateTime: LocalDateTime,
    val ticketCount: Int,
    val paymentAmount: PaymentAmount,
    val paymentType: PaymentType = PaymentType.LOCAL_PAYMENT
) : Serializable {

    companion object {
        private const val TICKET_PRICE = 13_000

        fun from(movie: Movie, ticketCount: Int, screeningDateTime: LocalDateTime): Reservation {
            val paymentAmount: PaymentAmount = Discount().getPaymentAmountResult(
                PaymentAmount(ticketCount * TICKET_PRICE),
                screeningDateTime
            )
            return Reservation(
                movie = movie,
                screeningDateTime = screeningDateTime,
                ticketCount = ticketCount,
                paymentAmount = paymentAmount,
            )
        }
    }
}
