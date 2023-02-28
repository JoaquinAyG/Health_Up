package study.project.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
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
    private lateinit var takePictureLauncher: ActivityResultLauncher<Intent>
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        takePictureLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val imageBitmap = data?.extras?.get("data") as Bitmap
                binding.ivProfile.setImageBitmap(imageBitmap)
            }
        }
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                takePictureLauncher.launch(cameraIntent)
            } else {
                Toast.makeText(requireContext(), "Camera permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initView() {

        binding.apply {
            editAge.setText(profile.age.toString())
            editDays.setText(profile.capableDays)
            editName.setText(profile.username)
            editWeight.setText(profile.weight.toString())
            editHeight.setText(profile.height.toString())
            editGender.setText(profile.gender)
            imageView.setOnClickListener {
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
            ivProfile.setOnClickListener {
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    takePictureLauncher.launch(cameraIntent)
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.CAMERA)
                }
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