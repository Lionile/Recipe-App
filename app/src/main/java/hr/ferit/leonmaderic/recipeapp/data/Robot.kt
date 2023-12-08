package hr.ferit.leonmaderic.recipeapp.data

class Robot{
    var x: Int = 0
    var y: Int = 0

    fun goRight(steps: Int){
        x += steps
    }

    fun goLeft(steps: Int){
        x -= steps
    }

    fun goUp(steps: Int){
        y += steps
    }

    fun goDown(steps: Int){
        y -= steps
    }

    fun getLocation(){
        println("(${x},${y})")
    }
}