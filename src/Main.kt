import java.io.File
import java.io.FileNotFoundException
import java.util.*

val letters = listOf("a","b","c","d","e","f","g","h")
val map = mapOf(
    "a" to 0,
    "b" to 1,
    "c" to 2,
    "d" to 3,
    "e" to 4,
    "f" to 5,
    "g" to 6,
    "h" to 7
)

fun main(vararg a : String){

    val file = File("in.txt")

    try{
        val input = file.inputStream()
        val sc = Scanner(input)


        var line = sc.nextLine()

        val horse = Position(map[line[0].toString()]!!, line[1].toString().toInt() - 1)

        line = sc.nextLine()

        val peshka = Position(map[line[0].toString()]!!, line[1].toString().toInt() - 1)


        val field = Array(8) {
            Array(8) {
                false
            }
        }

        val x1 = peshka.x - 1
        val x2 = peshka.x + 1

        val y = peshka.y - 1

        if((y in 0..7)){
            if(x1 in 0..7)
                field[x1][y] = true
            if(x2 in 0..7)
                field[x2][y] = true
        }

        val builder = mutableListOf<String>()

        find(horse.x, horse.y, peshka, field, builder)

        builder.add(repr(horse.x, horse.y))
        builder.reverse()


        /*builder.forEach {
            println(it)
        }*/


        File("out.txt").writeText(builder.joinToString("\n"))
    } catch (e : FileNotFoundException){
        println("in.txt file doesn't exists")
    }

}

fun find(x : Int, y : Int, position: Position, field : Array<Array<Boolean>>, builder: MutableList<String>) : Boolean{
    if(x == position.x && y == position.y)
        return true

    if (x >= 7 || x < 0 || y >= 7 || y < 0 || field[x][y])
        return false

    field[x][y] = true

    if(find(x - 1, y + 2, position, field, builder)){
        builder.add(repr(x - 1, y + 2))
        return true
    }
    if(find(x + 1, y + 2, position, field, builder)){
        builder.add(repr(x + 1, y + 2))
        return true
    }
    if(find(x + 2, y + 1, position, field, builder)){
        builder.add(repr(x + 2, y + 1))
        return true
    }
    if(find(x + 2, y - 1, position, field, builder)){
        builder.add(repr(x + 2, y - 1))
        return true
    }
    if(find(x + 1, y - 2, position, field, builder)){
        builder.add(repr(x + 1, y - 2))
        return true
    }
    if(find(x - 1, y - 2, position, field, builder)){
        builder.add(repr(x - 1, y - 2))
        return true
    }
    if(find(x - 2, y - 1, position, field, builder)){
        builder.add(repr(x - 2, y - 1))
        return true
    }
    if(find(x - 2, y + 1, position, field, builder)){
        builder.add(repr(x - 2, y + 1))
        return true
    }

    return false
}

fun repr(x : Int, y : Int) : String {
    return letters[x] + (y+1)
}

data class Position (val x : Int, val y : Int)