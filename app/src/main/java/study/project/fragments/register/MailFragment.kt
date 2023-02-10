package study.project.fragments.register


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import study.project.databinding.FragmentEmailBinding
import study.project.utils.isMail

class MailFragment : RegisterFragmentBase() {

    private var _binding: FragmentEmailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEmailBinding.inflate(inflater, container, false)
        binding.etMail.requestFocus()
        return binding.root
    }

    override fun commitChanges(): Boolean {
        binding.apply {
            if (etMail.text.toString().isEmpty()) {
                etMail.error = "Please enter your mail"
                return false
            }
            if (!etMail.text.toString().isMail()){
                etMail.error = "That mail is not valid"
                return false
            }

            //TODO Check if mail is already in use
            viewModel.updateEmail(etMail.text.toString())
            viewModel.notifyChange()
            return true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}