import duck.*
import duck.behavior.fly.FlyRocketPowered
import duck.behavior.fly.FlyWithCounting

fun main() {
    val mallardDuck = MallardDuck()
    val redheadDuck = RedheadDuck()
    val rubberDuck = RubberDuck()
    val decoyDuck = DecoyDuck()

    decoyDuck.flyBehavior = FlyWithCounting(FlyRocketPowered())

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