package fragment.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.sentance.yadi.databinding.FragmentDetailsBinding
import fragment.restart.DetailsViewmodel


class DetailsFragment : Fragment() {
    private val detailsViewmodel: DetailsViewmodel by activityViewModels()
    private var _binding: FragmentDetailsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailsName.text = detailsViewmodel.playerOnEdit.value?.name
        binding.detailsTxt.editText?.setText(detailsViewmodel.playerOnEdit.value?.text)
        binding.detailsSentance.editText?.setText(detailsViewmodel.playerOnEdit.value?.text)
        binding.detailsValidTv.setOnClickListener {
            detailsViewmodel.updatePlayer(
                binding.detailsTxt.editText?.text.toString(),
                binding.detailsSentance.editText?.text.toString()
            )
            findNavController().popBackStack()
        }
    }

}
