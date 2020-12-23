package fragment.register

import Player
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewmodel : ViewModel() {
    private var _listPlayer = MutableLiveData<MutableList<Player>>()
    val listPlayer: LiveData<MutableList<Player>>
        get() = _listPlayer

    init {
        _listPlayer.value = mutableListOf()
    }

    fun addPlayer(player: Player) {
        val tempList = mutableListOf<Player>()
        tempList.addAll(listPlayer.value!!)
        tempList.add(player)
        _listPlayer.value = tempList

    }

    fun deletePlayer(player: Player) {
        val tempList = mutableListOf<Player>()
        tempList.addAll(listPlayer.value!!)
        tempList.remove(player)
        _listPlayer.value = tempList
    }

    fun getAll(): MutableList<Player> {
        return listPlayer.value!!
    }
}