package duck

import duck.behavior.dance.DanceWaltz
import duck.behavior.fly.FlyWithWings
import duck.behavior.quack.Quack

class MallardDuck : Duck(
    flyBehavior = FlyWithWings(),
    quackBehavior = Quack(),
    danceBehavior = DanceWaltz()
) {
    override fun display() {
        println("Mallard duck")
    }
}