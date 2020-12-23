package fragment.register

import ListP
import Player
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sentance.yadi.R
import com.sentance.yadi.databinding.FragmentRegisterBinding
import recycleview.register.RegisterAdapter
import java.util.*


class RegisterFragment : Fragment() {

    private val registerViewmodel: RegisterViewmodel by activityViewModels()
    private var _binding: FragmentRegisterBinding? = null
    private val binding
        get() = _binding!!
    lateinit var registerAdapter: RegisterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerAdapter = RegisterAdapter(true)
        bindui()
        obsList()
        buttInfos()
        binding.mainHardTv.setOnClickListener {
            Util.Util.print(requireContext(), "BIENTÔT DISPONIBLE")
        }

    }


    private fun addPlayer() {
        val name = binding.mainPlayerNameEt.editText?.text.toString()
        binding.mainPlayerNameEt.editText?.text?.clear()
        val challenge = binding.mainPlayerChallengeEt.editText?.text.toString()
        binding.mainPlayerChallengeEt.editText?.text?.clear()
        val text = binding.mainPlayerTextEt.editText?.text.toString()
        binding.mainPlayerTextEt.editText?.text?.clear()
        registerViewmodel.addPlayer(Player(UUID.randomUUID(), name, challenge, text, 0, true, true))

    }

    private fun obsList() {
        registerViewmodel.listPlayer.observe(viewLifecycleOwner, {
            registerAdapter.submitList(it)
            // registerAdapter.notifyDataSetChanged()
        })
    }

    private fun bindui() {
        binding.mainRv.layoutManager = LinearLayoutManager(requireContext())
        registerAdapter.onItemDelete = { player: Player ->
            registerViewmodel.deletePlayer(player)
            Log.i("TAG", registerViewmodel.listPlayer.value?.size.toString())
        }
        registerAdapter.onClick = {}
        binding.mainRv.adapter = registerAdapter
        binding.mainAddIv.setOnClickListener {
            addPlayer()
        }
        obsList()


        binding.mainPlayText.setOnClickListener {
            if (registerViewmodel.listPlayer.value?.size!! < 3) Util.Util.print(
                requireContext(),
                "Veuillez rentrer 3 joueurs minimum"
            )
            else {
                val currentPlayer = registerViewmodel.listPlayer.value!!.filter { it.playerInLoop }.random()
                val ip = registerViewmodel.listPlayer.value!!.indexOf(currentPlayer)
                registerViewmodel.listPlayer.value!![ip].playerInLoop = false

                val currentText =
                    registerViewmodel.listPlayer.value!!.filter { it.textInLoop && it.id != currentPlayer.id }
                        .random()
                val l = registerViewmodel.listPlayer.value!!.indexOf(currentText)
                registerViewmodel.listPlayer.value!![l].textInLoop = false

                val list = ListP(registerViewmodel.listPlayer.value!!)
                val i = RegisterFragmentDirections.actionRegisterFragmentToTourFragment(
                    list,
                    currentPlayer,
                    currentText
                )
                findNavController().navigate(i)
            }


        }
    }
private fun buttInfos(){
    binding.floatingActionButton.setOnClickListener {
        findNavController().navigate(R.id.action_registerFragment_to_rulesFragment)
    }
}

}