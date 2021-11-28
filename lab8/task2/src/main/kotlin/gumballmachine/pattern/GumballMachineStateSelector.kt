package gumballmachine.pattern

import gumballmachine.pattern.state.GumballMachineState

interface GumballMachineStateSelector {
    fun setSoldOutState()
    fun setNoQuarterState()
    fun setSoldState()
    fun setHasQuarterState()
    fun getState(): GumballMachineState
    fun releaseBall()
    fun getBallCount(): UInt
    fun getQuarterCount(): UInt
    fun addQuarter()
    fun ejectAllQuarters()
}