package Util

import ListP
import android.content.Context
import android.widget.Toast

object Util {
    fun print(context: Context, string: String){
        Toast.makeText(context, string , Toast.LENGTH_SHORT).show()
    }
    fun sortCurrent(list: ListP){
       /* val currentPlayer = list.listPlyers.filter {it.playerInLoop}.random()
        val i = list.listPlyers.indexOf(currentPlayer)
        list.listPlyers[i].playerInLoop = false

        val currentText = list.listPlyers.filter {it.textInLoop && it.id != currentPlayer.id}.random()
        val l = list.listPlyers.indexOf(currentText)
        list.listPlyers[l].textInLoop = false */


    }
}