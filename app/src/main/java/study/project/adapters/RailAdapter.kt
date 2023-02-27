package study.project.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import study.project.databinding.RailItemBinding
import study.project.holders.RailHolder
import study.project.models.Exercise

class RailAdapter(
    private val exerciseList: List<Exercise>,
    private val categoryList: List<String>,
    private val onFavourite: (Exercise) -> Unit,
    private val onClick: (Exercise) -> Unit
) : RecyclerView.Adapter<RailHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RailHolder {
        return RailHolder(
            RailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onFavourite,
            onClick
        )
    }

    override fun onBindViewHolder(holder: RailHolder, position: Int) {
        val data = exerciseList.filter { it.category == categoryList[position] }
        holder.bind(data, categoryList[position])
        holder.binding.rvExercises.adapter?.notifyDataSetChanged()
    }

    override fun getItemCount() = categoryList.size
}
