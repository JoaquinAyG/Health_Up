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
        binding.etAge.requestFocus()
        return binding.root
    }

    override fun commitChanges(): Boolean {
        binding.apply {
            if (etAge.text.toString().isEmpty()) {
                etAge.error = "Please enter your Age"
                return false
            }
            if (!etAge.text.toString().isNumber()) {
                etAge.error = "Please enter a valid Age"
                return false
            }
            if (etAge.text.toString().toInt() < 4 || etAge.text.toString().toInt() > 100) {
                etAge.error = "Please enter a valid Age"
                return false
            }
            viewModel.updateAge(etAge.text.toString().toInt())
            return true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}