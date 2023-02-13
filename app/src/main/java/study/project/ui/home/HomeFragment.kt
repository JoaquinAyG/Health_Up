package study.project.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import study.project.HealthUpApplication
import study.project.activities.MainActivity
import study.project.adapters.RailAdapter
import study.project.databinding.FragmentHomeBinding
import study.project.factories.UserViewModelFactory
import study.project.models.Exercise
import study.project.viewmodels.ExerciseViewModel
import study.project.viewmodels.UserViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ExerciseViewModel by lazy {
        ViewModelProvider(this)[ExerciseViewModel::class.java]
    }
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((requireActivity().application as HealthUpApplication).repository)
    }
    private val exerciseList = mutableListOf<Exercise>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvRails.layoutManager = layoutManager
        binding.rvRails.adapter = RailAdapter(
            exerciseList,
            viewModel.categoryList,
            onFavourite = {
                userViewModel.updateFavoriteExercise(it)
            },
            onClick = {
                (requireActivity() as MainActivity).navigateUpTo(
                    (requireActivity() as MainActivity).getIntentForExercise(it)
                )
            }
        )
        viewModel.fetchData()
        viewModel.status.observe(viewLifecycleOwner){ status ->
            when (status) {
                "SUCCESS" -> {
                    binding.loadingAnimation.visibility = View.GONE
                }
                "LOADING" -> {
                    binding.loadingAnimation.visibility = View.VISIBLE
                }
            }
        }
        viewModel.exerciseList.observe(viewLifecycleOwner){ exercises ->
            exercises.forEach { exercise ->
                if (!exerciseList.map { it.nameEn.ifEmpty { it.name } }.contains(exercise.nameEn.ifEmpty { exercise.name })) {
                    exerciseList.add(exercise)
                }
            }
            binding.rvRails.adapter = RailAdapter(exerciseList, viewModel.categoryList,
                onFavourite = {

                },
                onClick = {

                })
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}