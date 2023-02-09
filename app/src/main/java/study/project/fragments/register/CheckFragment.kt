package study.project.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import study.project.databinding.FragmentCheckBinding
import study.project.models.User

class CheckFragment : RegisterFragmentBase() {

    private var _binding: FragmentCheckBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView(){
        val user = if (viewModel.user.value == null) {
            User()
        } else {
            viewModel.user.value!!
        }
        binding.apply {
            editGender.text = user.gender
            editAge.text = user.age.toString()
            editDays.text = user.capableDays
            editEmail.text = user.email
            editName.text = user.username
            editWeight.text = user.weight.toString()
            editHeight.text = user.height.toString()

        }
    }
    override fun commitChanges(): Boolean {
        return (viewModel.user.value != null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @Deprecated("Deprecated in Java")
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            initView()
        }
    }
}