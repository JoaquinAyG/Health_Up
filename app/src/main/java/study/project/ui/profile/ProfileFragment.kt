package study.project.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import study.project.databinding.FragmentProfileBinding
import study.project.models.User
import study.project.models.UserProfile
import study.project.viewmodels.UserViewModel

class ProfileFragment : Fragment() {

    private  val profile= UserProfile.instance
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
                ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initView()
        return root
    }
    private fun initView(){

        binding.apply {
            editAge.setText(profile.age.toString())
            editDays.setText(profile.capableDays)
            editName.text = profile.username
            editWeight.setText(profile.weight.toString())
            editHeight.setText(profile.height.toString())

        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}