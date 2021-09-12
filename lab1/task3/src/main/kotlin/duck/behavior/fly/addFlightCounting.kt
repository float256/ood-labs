package duck.behavior.fly

fun addFlightCounting(flightFunction: () -> Unit): () -> Unit {
    var numberFlight = 0
    return {
        flightFunction()
        numberFlight++
        println("Number of flight: $numberFlight")
    }
}
