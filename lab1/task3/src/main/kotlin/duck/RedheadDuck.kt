package duck

import duck.behavior.dance.danceMinuet
import duck.behavior.fly.addFlightCounting
import duck.behavior.fly.flyWithWings
import duck.behavior.quack.quack

class RedheadDuck : Duck(
    fly = addFlightCounting(::flyWithWings),
    quack = ::quack,
    dance = ::danceMinuet
) {
    override fun display() {
        println("Redhead ducks")
    }
}