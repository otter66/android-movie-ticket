package woowacourse.movie.domain.discount

import woowacourse.movie.domain.payment.PaymentAmount
import java.time.LocalDateTime

class Discount : DiscountRule {
    override fun getPaymentAmountResult(
        paymentAmount: PaymentAmount,
        screeningDateTime: LocalDateTime
    ): PaymentAmount {
        val discounts: List<DiscountRule> = listOf(
            MovieDayDiscount(),
            EarlyNightDiscount()
        )
        var resultPaymentAmount: PaymentAmount = paymentAmount

        discounts.forEach {
            resultPaymentAmount = it.getPaymentAmountResult(resultPaymentAmount, screeningDateTime)
        }

        return resultPaymentAmount
    }
}
