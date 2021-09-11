package duck

import duck.behavior.quack.quack

class DecoyDuck : Duck(
    fly = {},
    quack = {},
    dance = {}
) {
    override fun display() {
        println("Decoy duck")
    }
}