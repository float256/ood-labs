package duck.behavior.fly

class FlyWithCounting(
    val flyBehavior: FlyBehavior
) : FlyBehavior {
    private var numberOfFlight = 0

    override fun fly() {
        flyBehavior.fly()
        numberOfFlight++
        println("Number of flight: $numberOfFlight")
    }
}