package study.project.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import study.project.databinding.FragmentNameBinding
import study.project.databinding.FragmentWeightBinding
import study.project.utils.isNumber

class NameFragment : RegisterFragmentBase() {

    private var _binding: FragmentNameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNameBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun commitChanges(): Boolean {
        binding.apply {
            if (etName.text.toString().isEmpty()) {
                etName.error = "Please enter your name"
                return false
            }
            viewModel.updateName(etName.text.toString())
            return true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}