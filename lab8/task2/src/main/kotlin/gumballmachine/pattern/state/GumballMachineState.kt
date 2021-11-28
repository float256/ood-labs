package gumballmachine.pattern.state

interface GumballMachineState {
    fun insertQuarter()
    fun ejectQuarter()
    fun turnCrank()
    fun dispense()
}