package fragment.rank

import ListP
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sentance.yadi.databinding.FragmentRankBinding
import recycleview.board.BoardAdapter
import java.util.*


class RankFragment : Fragment() {
    val args: RankFragmentArgs by navArgs()
    private var _binding: FragmentRankBinding? = null
    private val binding
        get() = _binding!!
    lateinit var listPlayers: ListP


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listPlayers = args.list
        /*listPlayers = ListP(mutableListOf<Player>(
            Player(UUID.randomUUID(), "adi", "adi", "adi", 1, false, false),
            Player(UUID.randomUUID(), "adi", "adi", "adi", 2, false, false),
            Player(UUID.randomUUID(), "adi", "adi", "adi", 3, false, false),
            Player(UUID.randomUUID(), "adi", "adi", "adi", 0, false, false)
        ))*/
        val adapter = BoardAdapter()
        binding.rankRv.layoutManager = LinearLayoutManager(requireContext())
        adapter.submitList(listPlayers.listPlyers.sortedBy { it.score }.reversed())
        binding.rankRv.adapter = adapter
        binding.rankPlayText.setOnClickListener {
            val i = RankFragmentDirections.actionRankFragment2ToRestartFragment(listPlayers)
            findNavController().navigate(i)
        }
    }


}