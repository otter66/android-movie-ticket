package woowacourse.movie.activity.seats

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import payment.PaymentAmount
import reservation.TicketCount
import woowacourse.movie.activity.ReservationResultActivity
import woowacourse.movie.databinding.ActivitySeatSelectionBinding
import woowacourse.movie.uimodel.MovieModel
import woowacourse.movie.uimodel.MovieModel.Companion.MOVIE_INTENT_KEY
import woowacourse.movie.uimodel.ReservationModel
import woowacourse.movie.uimodel.ReservationModel.Companion.RESERVATION_INTENT_KEY
import woowacourse.movie.uimodel.ReservationModel.Companion.SCREENING_DATE_TIME_INTENT_KEY
import woowacourse.movie.uimodel.TicketCountModel.Companion.TICKET_COUNT_INTENT_KEY
import woowacourse.movie.uimodel.toSeatModel
import java.time.LocalDateTime

class SeatSelectionActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySeatSelectionBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val movieModel: MovieModel = intent.getSerializableExtra(MOVIE_INTENT_KEY) as MovieModel
        val ticketCount: Int = intent.getIntExtra(TICKET_COUNT_INTENT_KEY, TicketCount.MINIMUM)
        binding.movieNameTextView.text = movieModel.name.value

        val seatsView = SeatsView(binding, intent)
        seatsView.set()
        binding.reservationCompleteTextView.setOnClickListener { completeButtonClickEvent(seatsView, ticketCount) }
    }

    private fun completeButtonClickEvent(seatsView: SeatsView, ticketCount: Int) {
        if (seatsView.getSelectedCount() == ticketCount) showDialog(seatsView, ticketCount)
    }

    private fun showDialog(seatsView: SeatsView, ticketCount: Int) {
        AlertDialog.Builder(this)
            .setTitle("예매 확인")
            .setMessage("정말 예매하시겠습니까?")
            .setPositiveButton("예매 완료") { _, _ ->
                val movieModel: MovieModel =
                    intent.getSerializableExtra(MOVIE_INTENT_KEY) as MovieModel
                val screeningDateTime: LocalDateTime =
                    intent.getSerializableExtra(SCREENING_DATE_TIME_INTENT_KEY) as LocalDateTime

                val nextIntent = Intent(this, ReservationResultActivity::class.java)
                val paymentAmount =
                    PaymentAmount(binding.paymentAmountTextView.text.toString().toInt())

                val reservationModel = ReservationModel(
                    movie = movieModel,
                    screeningDateTime = screeningDateTime,
                    ticketCount = ticketCount,
                    seats = seatsView.getSelectedSeats().map { it.toSeatModel() },
                    paymentAmount = paymentAmount
                )

                nextIntent.putExtra(RESERVATION_INTENT_KEY, reservationModel)
                startActivity(nextIntent)
            }
            .setNegativeButton("취소") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
