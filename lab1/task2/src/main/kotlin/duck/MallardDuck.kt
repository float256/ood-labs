package duck

import duck.behavior.dance.DanceWaltz
import duck.behavior.fly.FlyWithCounting
import duck.behavior.fly.FlyWithWings
import duck.behavior.quack.Quack

class MallardDuck : Duck(
    flyBehavior = FlyWithCounting(FlyWithWings()),
    quackBehavior = Quack(),
    danceBehavior = DanceWaltz()
) {
    override fun display() {
        println("Mallard duck")
    }
}