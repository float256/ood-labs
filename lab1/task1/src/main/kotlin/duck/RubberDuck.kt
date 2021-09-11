package duck

import duck.behavior.dance.DanceNoWay
import duck.behavior.fly.FlyNoWay
import duck.behavior.quack.Squeak

class RubberDuck() : Duck(
    flyBehavior = FlyNoWay(),
    quackBehavior = Squeak(),
    danceBehavior = DanceNoWay()
) {
    override fun display() {
        println("Rubber duck")
    }
}