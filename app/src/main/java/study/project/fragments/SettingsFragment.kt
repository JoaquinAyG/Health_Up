package study.project.fragments

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import study.project.HealthUpApplication
import study.project.activities.SettingsActivity
import study.project.databinding.FragmentSettingsBinding
import study.project.factories.UserViewModelFactory
import study.project.models.UserProfile
import study.project.viewmodels.UserViewModel

class SettingsFragment : Fragment() {

    private val profile = UserProfile.instance
    private var _binding: FragmentSettingsBinding? = null
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
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        configureClickListeners()

        return binding.root
    }

    private fun configureClickListeners() {
        binding.apply {
            btnAboutUs.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    val url = "https://github.com/JoaquinAyG/Health_Up"
                    data = url.toUri()
                }
                startActivity(intent)
            }
            btnLogOut.setOnClickListener {
                (requireActivity() as SettingsActivity).logOut()
            }
            btnChangePassword.setOnClickListener {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle("New Password")
                val inputLayout = LinearLayout(requireContext())
                inputLayout.orientation = LinearLayout.VERTICAL
                val passwordInput = EditText(requireContext())
                passwordInput.hint = "Password"
                passwordInput.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                inputLayout.addView(passwordInput)
                val rPasswordInput = EditText(requireContext())
                rPasswordInput.hint = "Repeat Password"
                rPasswordInput.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                inputLayout.addView(rPasswordInput)
                builder.setView(inputLayout)
                builder.setPositiveButton("Change Password") { _, _ ->
                    val password = passwordInput.text.toString()
                    val rPassword = rPasswordInput.text.toString()
                    if (password == rPassword) {
                        userViewModel.update(profile.apply { this.password = password })
                    } else {
                        AlertDialog.Builder(requireContext())
                            .setTitle("Passwords don't match")
                            .setPositiveButton("Ok", null)
                            .show()
                    }
                }
                builder.setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }
                builder.show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}