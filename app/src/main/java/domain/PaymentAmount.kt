package domain

@JvmInline
value class PaymentAmount(val value: Int) {
    init {
        require(value >= 0)
    }
}