package study.project.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import study.project.databinding.FragmentPasswordBinding

class PasswordFragment : RegisterFragmentBase(){

    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!

    override fun commitChanges(): Boolean {
        binding.apply{
            if (etPassword.text.toString().isEmpty()) {
                etPassword.error = "Please enter a password"
                return false
            }

            return if (etPassword.text.toString() == etConfirmPassword.text.toString()){
                viewModel.updatePassword(etPassword.text.toString())
                true
            } else {
                etPassword.error = "Passwords do not match"
                false
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        binding.etPassword.requestFocus()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}