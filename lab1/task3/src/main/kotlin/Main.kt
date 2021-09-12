import duck.*
import duck.behavior.fly.addFlightCounting
import duck.behavior.fly.flyRockedPowered

fun main() {
    val mallardDuck = MallardDuck()
    val redheadDuck = RedheadDuck()
    val rubberDuck = RubberDuck()
    val decoyDuck = DecoyDuck()

    val func = addFlightCounting(::flyRockedPowered)
    decoyDuck.fly = func

    runAllActionsForDuck(mallardDuck)
    runAllActionsForDuck(redheadDuck)
    runAllActionsForDuck(rubberDuck)
    runAllActionsForDuck(decoyDuck)
    func()
}

fun runAllActionsForDuck(duck: Duck) {
    duck.display()
    duck.quack()
    duck.fly()
    duck.fly()
    duck.fly()
    duck.swim()
    duck.dance()
    println()
}