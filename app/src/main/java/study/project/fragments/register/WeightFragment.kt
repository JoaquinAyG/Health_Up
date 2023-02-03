package study.project.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import study.project.databinding.FragmentWeightBinding
import study.project.utils.isNumber

class WeightFragment : RegisterFragmentBase() {

    private var _binding: FragmentWeightBinding? = null
    private val binding get() = _binding!!
    override fun commitChanges(): Boolean {
        binding.apply {
            if (etWeight.text.toString().isEmpty()) {
                etWeight.error = "Please enter your weight"
                return false
            }

            if (etWeight.text.toString().isNumber()){
                viewModel.updateWeight(etWeight.text.toString().toDouble())
                return true
            } else {
                etWeight.error = "Please enter a valid weight"
                return false
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeightBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}