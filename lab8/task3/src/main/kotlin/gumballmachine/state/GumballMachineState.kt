package gumballmachine.state

interface GumballMachineState {
    fun insertQuarter()
    fun ejectQuarter()
    fun turnCrank()
    fun dispense()
    fun fillMachine(numberOfGumballs: UInt)
}