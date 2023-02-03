package study.project.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import study.project.databinding.FragmentAgeBinding
import study.project.utils.isNumber

class AgeFragment : RegisterFragmentBase() {

    private var _binding: FragmentAgeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun commitChanges(): Boolean {
        binding.apply {
            if (etAge.text.toString().isEmpty()) {
                etAge.error = "Please enter your Age"
                return false
            }

            return if (etAge.text.toString().isNumber()){
                viewModel.updateAge(etAge.text.toString().toInt())
                true
            } else {
                etAge.error = "Please enter a valid Age"
                false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}