package duck

import duck.behavior.dance.DanceNoWay
import duck.behavior.fly.FlyNoWay
import duck.behavior.quack.MuteQuack

class DecoyDuck : Duck(
    flyBehavior = FlyNoWay(),
    quackBehavior = MuteQuack(),
    danceBehavior = DanceNoWay()
) {
    override fun display() {
        println("Decoy duck")
    }
}