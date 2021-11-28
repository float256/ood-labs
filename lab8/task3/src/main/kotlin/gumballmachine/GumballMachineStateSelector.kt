package gumballmachine

import gumballmachine.state.GumballMachineState

interface GumballMachineStateSelector {
    fun setSoldOutState()
    fun setNoQuarterState()
    fun setSoldState()
    fun setHasQuarterState()
    fun getState(): GumballMachineState

    fun releaseBall()
    fun getBallCount(): UInt
    fun addBalls(numberOfGumballs: UInt)

    fun getQuarterCount(): UInt
    fun addQuarter()
    fun ejectAllQuarters()
}