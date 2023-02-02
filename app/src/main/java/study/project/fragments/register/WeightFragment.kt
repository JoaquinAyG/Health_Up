package study.project.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import study.project.databinding.FragmentWeightBinding
import study.project.utils.isNumber

class WeightFragment : Fragment() {

    private var _binding: FragmentWeightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeightBinding.inflate(inflater, container, false)
        binding.etWeight.requestFocus()
        return binding.root
    }

    override fun commitChanges(): Boolean {
        binding.apply {
            if (etWeight.text.toString().isEmpty()) {
                etWeight.error = "Please enter your weight"
                return false
            }

            return if (etWeight.text.toString().isNumber()){
                viewModel.updateWeight(etWeight.text.toString().toDouble())
                true
            } else {
                etWeight.error = "Please enter a valid weight"
                false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}