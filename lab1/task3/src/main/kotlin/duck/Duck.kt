package duck

abstract class Duck(
    var fly: () -> Unit,
    var quack: () -> Unit,
    var dance: () -> Unit
) {
    abstract fun display()

    fun swim() {
        println("Swim")
    }
}