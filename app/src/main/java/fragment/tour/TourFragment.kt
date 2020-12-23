package fragment.tour

import ListP
import NameAdapter
import Player
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.sentance.yadi.databinding.FragmentTourBinding
import java.util.*

class TourFragment : Fragment(), TextToSpeech.OnInitListener {
    val args: TourFragmentArgs by navArgs()
    private var _binding: FragmentTourBinding? = null
    private val binding
        get() = _binding!!
    lateinit var listPlayers: ListP
    lateinit var currentPlayer: Player
    lateinit var currentTextPlayer: Player
    lateinit var textToSpeech: TextToSpeech


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTourBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listPlayers = args.list
        currentPlayer = args.currentPlayer
        currentTextPlayer = args.currentTextPlayer
        textToSpeech = TextToSpeech(requireContext(), this)
        speaker()
        bindUi()
        buildRecycleView()


    }

    private fun bindUi() {
        binding.questionStepTvNameAnounceCard.text = currentPlayer.name
        binding.questionStepTvSentanceAnounceCard.text = currentPlayer.challenge
        binding.questionStepPhraseTv.text = currentTextPlayer.text
    }

    private fun speaker() {
        binding.questionStepPlayIv.setOnClickListener {
            textToSpeech.speak(currentTextPlayer.text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    private fun buildRecycleView(){
        val nameAdapter = NameAdapter()
        binding.questionStepRecycleview.layoutManager = GridLayoutManager(requireContext(), 3)
        nameAdapter.onItemClick = {
            var isGood = false
            if(it == currentTextPlayer)
                isGood = true
            val i = TourFragmentDirections.actionTourFragmentToResponseFragment(listPlayers, currentPlayer, currentTextPlayer, isGood)
            findNavController().navigate(i)

        }
        nameAdapter.submitList(listPlayers.listPlyers.filter { it.id != currentPlayer.id })
        binding.questionStepRecycleview.adapter = nameAdapter
    }

    override fun onInit(status: Int) {
        if (status != TextToSpeech.ERROR) {
            textToSpeech.language = Locale.FRANCE
        }
    }

}