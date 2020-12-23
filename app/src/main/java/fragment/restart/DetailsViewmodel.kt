package fragment.restart

import ListP
import Player
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class DetailsViewmodel : ViewModel() {

    private var _playerOnEdit = MutableLiveData<Player>()
    val playerOnEdit: LiveData<Player>
        get() = _playerOnEdit

    private var _listPlayer = MutableLiveData<MutableList<Player>>()
    val listPlayer: LiveData<MutableList<Player>>
        get() = _listPlayer

    init {
        _listPlayer.value =  mutableListOf()
    }


    fun goToEdit(player: Player) {
        _playerOnEdit.value = player
    }
    fun updatePlayer(txt : String, sentance : String){
        val newList = mutableListOf<Player>()
        newList.addAll(listPlayer.value!!)
        val i = newList.indexOf(playerOnEdit.value)
        newList[i].text = txt
        newList[i].challenge = sentance
        newList[i].playerInLoop = true
        newList[i].textInLoop = true


        _listPlayer.value = newList

    }

    fun setlist(listP: ListP){
        var list = mutableListOf<Player>()
        list.addAll(listP.listPlyers)
        _listPlayer.value = list
    }
}