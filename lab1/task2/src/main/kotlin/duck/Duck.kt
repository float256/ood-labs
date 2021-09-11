package duck

import duck.behavior.dance.DanceBehavior
import duck.behavior.fly.FlyBehavior
import duck.behavior.quack.QuackBehavior

abstract class Duck(
    var flyBehavior: FlyBehavior,
    var quackBehavior: QuackBehavior,
    var danceBehavior: DanceBehavior
) {
    fun fly() = flyBehavior.fly()
    fun quack() = quackBehavior.quack()
    fun dance() = danceBehavior.dance()
    abstract fun display()

    fun swim() {
        println("Swim")
    }
}