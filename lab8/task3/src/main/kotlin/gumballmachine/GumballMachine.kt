package gumballmachine

interface GumballMachine {
    fun insertQuarter()
    fun ejectQuarter()
    fun turnCrank()
    fun fillMachine(numberOfGumballs: UInt)
}