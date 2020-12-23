package fragment.response

import ListP
import Player
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sentance.yadi.databinding.FragmentResponseBinding


class ResponseFragment : Fragment() {
    val args: ResponseFragmentArgs by navArgs()
    private var _binding: FragmentResponseBinding? = null
    private val binding
        get() = _binding!!
    lateinit var listPlayers: ListP
    lateinit var currentPlayer: Player
    lateinit var currentText: Player


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResponseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listPlayers = args.list
        currentPlayer = args.currentPlayer
        currentText = args.currentText
        bindResponse(args.goodResponse)
        binding.responseNextTv.setOnClickListener {
            if (listPlayers.listPlyers.filter { it.playerInLoop }.size == 2 || listPlayers.listPlyers.filter { it.playerInLoop }.isEmpty() ) {
                var ip = ResponseFragmentDirections.actionResponseFragmentToRankFragment2(listPlayers)
                findNavController().navigate(ip)
            } else {
                val currentPlayer = listPlayers.listPlyers.filter { it.playerInLoop }.random()
                val ip = listPlayers.listPlyers.indexOf(currentPlayer)
                listPlayers.listPlyers[ip].playerInLoop = false

                val currentText =
                    listPlayers.listPlyers.filter { it.textInLoop && it.id != currentPlayer.id }
                        .random()
                val l = listPlayers.listPlyers.indexOf(currentText)
                listPlayers.listPlyers[l].textInLoop = false

                val i = ResponseFragmentDirections.actionResponseFragmentToTourFragment(
                    listPlayers,
                    currentPlayer,
                    currentText
                )
                findNavController().navigate(i)
            }
        }
        refreshScore()
    }

    fun bindResponse(isGood: Boolean) {
        binding.responseResultTv.text = if (isGood) "Bonne réponse" else "Mauvaise réponse"
        binding.responseSentanceTv.text =
            "Sentance pour " + if (isGood) currentText.name else currentPlayer.name
        binding.responseSentanceTxt.text = currentPlayer.challenge
        binding.responseCardName.text = if (isGood) currentPlayer.name else currentText.name
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    private fun refreshScore(){
        val index = listPlayers.listPlyers.indexOf(if (args.goodResponse) currentPlayer else currentText)
        listPlayers.listPlyers[index].score ++
    }

}