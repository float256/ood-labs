package ui

import gumballmachine.GumballMachine
import gumballmachine.GumballMachineImpl
import java.io.InputStream
import java.io.PrintStream
import java.lang.IllegalArgumentException
import java.util.*

class GumballMachineUI {
    companion object {
        const val INPUT_PROMPT = ">> "
    }

    fun process(input: InputStream, output: PrintStream) {
        val scanner = Scanner(input)
        val gumballMachine = initMachine(scanner, output)
        while (true) {
            output.print(INPUT_PROMPT)
            if (scanner.hasNextLine()) {
                try {
                    processOneLine(scanner.nextLine(), output, gumballMachine)
                } catch (e: Exception) {
                    println(e)
                }
            } else {
                break
            }
        }
    }

    private fun initMachine(input: Scanner, output: PrintStream): GumballMachine {
        output.print("Number of gumballs: ")
        if (input.hasNext()) {
            val numberOfGumballs = input.nextLine().toUInt()
            return GumballMachineImpl(numberOfGumballs, output)
        } else {
            throw IllegalArgumentException("Initialization parameters for gumball machine were not provided")
        }
    }

    private fun processOneLine(line: String, output: PrintStream, machine: GumballMachine) {
        if (line.trim().isNotEmpty()) {
            val commandArguments = line.split(" ")
            when (commandArguments[0]) {
                GumballMachineCommands.Help.name -> printHelp(output)
                GumballMachineCommands.EjectQuarter.name -> ejectQuarter(machine)
                GumballMachineCommands.InsertQuarter.name -> insertQuarter(machine)
                GumballMachineCommands.TurnCrank.name -> turnCrank(machine)
                GumballMachineCommands.FillMachine.name -> fillMachine(commandArguments, machine)
                else -> println("Incorrect command")
            }
        }
    }

    private fun printHelp(output: PrintStream) {
        output.println(
            """
            Available commands:
            -- Help
            -- EjectQuarter
            -- InsertQuarter
            -- TurnCrank
            -- FillMachine <Number of added balls>
            """.trimIndent()
        )
    }

    private fun ejectQuarter(machine: GumballMachine) {
        machine.ejectQuarter()
    }

    private fun insertQuarter(machine: GumballMachine) {
        machine.insertQuarter()
    }

    private fun turnCrank(machine: GumballMachine) {
        machine.turnCrank()
    }

    private fun fillMachine(commandArguments: List<String>, machine: GumballMachine) {
        if (commandArguments.size == 2) {
            val numberOfGumballs = commandArguments[1].toUInt()
            machine.fillMachine(numberOfGumballs)
        } else {
            throw IllegalArgumentException("Incorrect number of arguments")
        }
    }
}