package study.project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import study.project.HealthUpApplication
import study.project.activities.MainActivity
import study.project.adapters.RailAdapter
import study.project.databinding.FragmentHomeBinding
import study.project.factories.FavViewModelFactory
import study.project.models.Exercise
import study.project.models.Fav
import study.project.models.UserProfile
import study.project.viewmodels.ExerciseViewModel
import study.project.viewmodels.FavViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ExerciseViewModel by viewModels()
    private val favViewModel: FavViewModel by viewModels {
        FavViewModelFactory((requireActivity().application as HealthUpApplication).favRepository)
    }
    private val exerciseList = mutableListOf<Exercise>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        bindViewModel()
        configureAdapter()
        subscribe()
        if (!viewModel.fetched) {
            viewModel.fetchData()
        }
        return root
    }

    private fun configureAdapter() {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvRails.layoutManager = layoutManager
        val adapter = RailAdapter(
            exerciseList,
            viewModel.categoryList,
            onFavourite = { exercise ->
                val fav = Fav(id = UserProfile.instance.id, exerciseId = exercise.id)
                favViewModel.insert(fav)
                exerciseList.find { it.id == fav.exerciseId }?.favourite = true
            },
            onClick = {
                (activity as MainActivity).navigateToExercise(it)

            }
        )
        binding.rvRails.adapter = adapter

    }

    private fun bindViewModel() {
        binding.lifecycleOwner = this@HomeFragment
    }

    private fun subscribe() {
        viewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                "SUCCESS" -> {
                    binding.loadingAnimation.visibility = View.GONE
                }
                "LOADING" -> {
                    binding.loadingAnimation.visibility = View.VISIBLE
                }
            }
        }
        viewModel.exerciseList.observe(viewLifecycleOwner) { exercises ->
            exercises.forEach { exercise ->
                if (!exerciseList.map { it.nameEn.ifEmpty { it.name } }
                        .contains(exercise.nameEn.ifEmpty { exercise.name })) {
                    exerciseList.add(exercise)
                }
            }
            binding.rvRails.adapter?.notifyDataSetChanged()
        }
        favViewModel.status.observe(viewLifecycleOwner) { status ->
            when (status) {
                "CHANGE" -> {
                    binding.loadingAnimation.visibility = View.GONE
                    viewModel.updateExerciseStatus()
                }
            }
        }
        viewModel.exerciseStatus.observe(viewLifecycleOwner) {
            when (it) {
                "CHANGE" -> {
                    viewModel.exerciseList.value?.forEach { exercise ->
                        exercise.favourite =
                            favViewModel.allFavs.value?.map { it.exerciseId }?.contains(exercise.id)
                                ?: false
                    }
                    binding.rvRails.adapter?.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}