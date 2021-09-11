package duck

import duck.behavior.dance.DanceMinuet
import duck.behavior.fly.FlyWithWings
import duck.behavior.quack.Quack

class RedheadDuck : Duck(
    flyBehavior = FlyWithWings(),
    quackBehavior = Quack(),
    danceBehavior = DanceMinuet()
) {
    override fun display() {
        println("Redhead ducks")
    }
}