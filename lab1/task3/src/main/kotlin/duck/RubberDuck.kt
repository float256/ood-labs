package duck

import duck.behavior.quack.squeak

class RubberDuck() : Duck(
    fly = {},
    quack = ::squeak,
    dance = {}
) {
    override fun display() {
        println("Rubber duck")
    }
}