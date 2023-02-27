package study.project.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import study.project.databinding.FragmentHeightBinding
import study.project.utils.isInt

class HeightFragment : RegisterFragmentBase() {

    private var _binding: FragmentHeightBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeightBinding.inflate(inflater, container, false)
        binding.etHeight.requestFocus()
        return binding.root
    }

    override fun commitChanges(): Boolean {
        binding.apply {
            if (etHeight.text.toString().isEmpty()) {
                etHeight.error = "Please enter your height"
                return false
            }
            if (!etHeight.text.toString().isInt()) {
                etHeight.error = "Please enter a valid height"
                return false
            }
            if (etHeight.text.toString().toInt() < 100 || etHeight.text.toString().toInt() > 250) {
                etHeight.error = "Please enter a valid height"
                return false
            }
            viewModel.updateHeight(etHeight.text.toString().toInt())
            return true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}