package study.project.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import study.project.R
import study.project.databinding.FragmentGenderBinding

class GenderFragment: RegisterFragmentBase(){

    private var _binding: FragmentGenderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGenderBinding.inflate(inflater, container, false)

        binding.apply {
            btnFemale.setOnClickListener {
                btnFemale.isChecked = true
                btnMale.isChecked = false
                btnOther.isChecked = false
            }
            btnMale.setOnClickListener {
                btnFemale.isChecked = false
                btnMale.isChecked = true
                btnOther.isChecked = false
            }
            btnOther.setOnClickListener {
                btnFemale.isChecked = false
                btnMale.isChecked = false
                btnOther.isChecked = true
            }
        }

        return binding.root
    }

    override fun commitChanges(): Boolean {
        binding.apply {
            if (!btnFemale.isChecked && !btnMale.isChecked && !btnOther.isChecked) {
                Toast.makeText(context, "Please select one of them, we are sorry if you are not represented here but we need to do a estimation", Toast.LENGTH_SHORT).show()
                return false
            } else {
                viewModel.updateGender(
                    when{
                        btnFemale.isChecked -> resources.getString(R.string.female)
                        btnOther.isChecked -> resources.getString(R.string.other)
                        else -> resources.getString(R.string.male)
                    }
                )
            }
            return true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}