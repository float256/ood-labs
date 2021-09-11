package duck.behavior.fly

fun addFlightCounting(flightFunction: () -> Unit): () -> Unit {
    return {
        flightFunction()
        println("Number of flight: ")
    }
}
