package duck

import duck.behavior.dance.danceWaltz
import duck.behavior.fly.addFlightCounting
import duck.behavior.fly.flyWithWings
import duck.behavior.quack.quack

class MallardDuck : Duck(
    fly = addFlightCounting(::flyWithWings),
    quack = ::quack,
    dance = ::danceWaltz
) {
    override fun display() {
        println("Mallard duck")
    }
}