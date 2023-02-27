package study.project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import study.project.HealthUpApplication
import study.project.databinding.FragmentProfileBinding
import study.project.factories.UserViewModelFactory
import study.project.models.UserProfile
import study.project.viewmodels.UserViewModel

class ProfileFragment : Fragment() {

    private val profile = UserProfile.instance
    private var _binding: FragmentProfileBinding? = null
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((requireActivity().application as HealthUpApplication).userRepository)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initView()
        return root
    }

    private fun initView() {

        binding.apply {
            editAge.setText(profile.age.toString())
            editDays.setText(profile.capableDays)
            editName.setText(profile.username)
            editWeight.setText(profile.weight.toString())
            editHeight.setText(profile.height.toString())
            editGender.setText(profile.gender)
            ivProfile.setOnClickListener {
                swapEnabled()
            }
            btnSave.setOnClickListener {
                profile.apply {
                    age = editAge.text.toString().toInt()
                    capableDays = editDays.text.toString()
                    username = editName.text.toString()
                    weight = editWeight.text.toString().toDouble()
                    height = editHeight.text.toString().toInt()
                }
                userViewModel.update(profile)
                swapEnabled()
            }
        }

        swapEnabled()
    }

    private fun swapEnabled() {
        val enabled = !binding.editName.isEnabled
        binding.apply {
            editAge.isEnabled = enabled
            editGender.isEnabled = enabled
            editDays.isEnabled = enabled
            editName.isEnabled = enabled
            editWeight.isEnabled = enabled
            editHeight.isEnabled = enabled
            btnSave.isVisible = enabled
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}