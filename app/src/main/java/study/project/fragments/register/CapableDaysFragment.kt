package study.project.fragments.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import study.project.databinding.FragmentCapableDaysBinding

class CapableDaysFragment : RegisterFragmentBase() {

    private var _binding: FragmentCapableDaysBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCapableDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun commitChanges(): Boolean {
        val list = mutableListOf<Int>()
        binding.apply {
            list.addAll(listOf(1, 2, 3, 4, 5, 6, 7).filter {
                when (it) {
                    1 -> btMonday.isChecked
                    2 -> btTuesday.isChecked
                    3 -> btWednesday.isChecked
                    4 -> btThursday.isChecked
                    5 -> btFriday.isChecked
                    6 -> btSaturday.isChecked
                    7 -> btSunday.isChecked
                    else -> false
                }
            })
        }
        viewModel.updateCapableDays(list)
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}