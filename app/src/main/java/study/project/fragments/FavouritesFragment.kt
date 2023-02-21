package study.project.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import study.project.HealthUpApplication
import study.project.activities.MainActivity
import study.project.adapters.FavouritesAdapter
import study.project.databinding.FragmentFavouritesBinding
import study.project.factories.FavViewModelFactory
import study.project.models.Exercise
import study.project.models.UserProfile
import study.project.viewmodels.ExerciseViewModel
import study.project.viewmodels.FavViewModel

class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavViewModel by viewModels {
        FavViewModelFactory((requireActivity().application as HealthUpApplication).favRepository)
    }
    private val exerciseViewModel: ExerciseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.notyfyChanges()

        binding.rvFavourites.layoutManager = GridLayoutManager(requireContext(), 3)

        if (!exerciseViewModel.fetched) {
            exerciseViewModel.fetchData()
        }
        exerciseViewModel.exerciseList.observe(viewLifecycleOwner) { exercises ->
            val list = mutableListOf<Exercise>()
            viewModel.allFavs.observe(viewLifecycleOwner) { favs ->

                favs.forEach { fav ->
                    exercises.forEach { exercise ->
                        if (fav.exerciseId == exercise.id && fav.id == UserProfile.instance.id) {

                            Log.i("ExerciseViewModel", "$exercise")
                            if (!list.contains(exercise)) list.add(exercise)
                            binding.rvFavourites.adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }
            binding.rvFavourites.adapter = FavouritesAdapter(
                list,
                onClick = {
                    Log.i("RailAdapter", "clicked")
                    (activity as MainActivity).navigateToExercise(it)
                }
            )
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}