package cinema

import java.lang.Exception

fun main() {
    var currentIncome = 0
    var tickets = 0
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()
        val cinema = mutableListOf(
            mutableListOf<String>(" ")
        )
        for (i in 1..rows) {
            cinema.add(mutableListOf("$i"))
            for (j in 1..seats) {
                cinema[i].add("S")
            }
        }
        for (i in 1..seats) {
            cinema[0].add("$i")
        }

    fun showTheSeats() {
        println("Cinema:")
        for(i in 0..rows) {
            println(cinema[i].joinToString(" "))
        }
    }

    fun buyTicket() {
        println("Enter a row number:")
        val r = readln().toInt()
        println("Enter a seat number in that row:")
        val s = readln().toInt()
        //IndexOutOfBoundsException
        var income = currentIncome
        try {
            if (cinema[r][s] == "S") {
                cinema[r][s] = "B"
                tickets += 1
                if (rows * seats < 60) {
                    println( "Ticket price: $10")
                    currentIncome += 10
                } else {
                    if(r > rows / 2) {
                        println("Ticket price: $8")
                        currentIncome += 8
                    } else {
                        println("Ticket price: $10")
                        currentIncome += 10
                    }
                }
            } else {
                println("That ticket has already been purchased!")
                buyTicket()
            }
        }
        catch (e: Exception) {
            println("Wrong input!")
            currentIncome = income
            buyTicket()
        }
    }

    fun statistics() {
        var percentage =  tickets.toDouble() / (rows.toDouble() * seats.toDouble()) * 100
        val formatPercentage = "%.2f".format(percentage)
        var totalIncome = 0
        if (rows * seats < 60) {
            totalIncome = rows * seats * 10
        } else {
            totalIncome = rows / 2 * seats * 10 + (rows - rows / 2) * seats * 8
        }
        println()
        println("Number of purchased tickets: $tickets")
        println("Percentage: $formatPercentage%")
        println("Current income: $$currentIncome")
        println("Total income: $$totalIncome")
    }
    fun menu() {
        println("\n"+
                "1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics \n" +
                "0. Exit")
        when(readln().toInt()) {
            1 -> {
                showTheSeats()
                menu()
            }
            2 -> {
                buyTicket()
                menu()
            }
            3 -> {
                statistics()
                menu()
            }
            0 -> return
            else -> menu()
        }
    }
    menu()
}
