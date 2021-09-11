import duck.*
import duck.behavior.fly.addFlightCounting
import duck.behavior.fly.flyRockedPowered

fun main() {
    val mallardDuck = MallardDuck()
    val redheadDuck = RedheadDuck()
    val rubberDuck = RubberDuck()
    val decoyDuck = DecoyDuck()

    decoyDuck.fly = addFlightCounting(::flyRockedPowered)

    runAllActionsForDuck(mallardDuck)
    runAllActionsForDuck(redheadDuck)
    runAllActionsForDuck(rubberDuck)
    runAllActionsForDuck(decoyDuck)
}

fun runAllActionsForDuck(duck: Duck) {
    duck.display()
    duck.quack()
    duck.fly()
    duck.swim()
    duck.dance()
    println()
}