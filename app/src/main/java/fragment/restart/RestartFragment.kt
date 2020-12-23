package fragment.restart

import ListP
import Player
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sentance.yadi.R
import com.sentance.yadi.databinding.FragmentRestartBinding
import recycleview.register.RegisterAdapter
import java.util.*


class RestartFragment : Fragment() {
    private val args : RestartFragmentArgs by navArgs()
    private val detailsViewmodel : DetailsViewmodel by activityViewModels()
    private var _binding: FragmentRestartBinding? = null
    private val binding
        get() = _binding!!
    lateinit var adapter: RegisterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRestartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RegisterAdapter(false)
        detailsViewmodel.setlist(args.list)
        adapter.onClick = {goToEditPlayer(it)}
        binding.restartRv.layoutManager = LinearLayoutManager(requireContext())
        binding.restartRv.adapter = adapter
        obsList()
        binding.mainPlayText.setOnClickListener {
            playRound()
        }

    }
    private fun goToEditPlayer(player: Player){
        if (!player.textInLoop){
            detailsViewmodel.goToEdit(player)
            findNavController().navigate(R.id.action_restartFragment_to_detailsFragment)
        } else {
            Util.Util.print(requireContext(), "ce joueur est deja enregistré")
        }
    }

    private fun obsList(){
        detailsViewmodel.listPlayer.observe(viewLifecycleOwner, {
           val newlist = mutableListOf<Player>()
            newlist.addAll(it)
            adapter.submitList(newlist)
        })
    }

    private fun playRound(){
        val currentPlayer = detailsViewmodel.listPlayer.value!!.filter {it.playerInLoop}.random()
        val ip = detailsViewmodel.listPlayer.value!!.indexOf(currentPlayer)
        detailsViewmodel.listPlayer.value!![ip].playerInLoop = false

        val currentText = detailsViewmodel.listPlayer.value!!.filter {it.textInLoop && it.id != currentPlayer.id}.random()
        val l = detailsViewmodel.listPlayer.value!!.indexOf(currentText)
        detailsViewmodel.listPlayer.value!![l].textInLoop = false

        val list = ListP(detailsViewmodel.listPlayer.value!!)
        val i = RestartFragmentDirections.actionRestartFragmentToTourFragment(list, currentPlayer, currentText)
        findNavController().navigate(i)
    }

}