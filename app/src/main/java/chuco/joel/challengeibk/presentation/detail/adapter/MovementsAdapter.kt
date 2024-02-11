package chuco.joel.challengeibk.presentation.detail.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import chuco.joel.challengeibk.databinding.ItemMovementBinding
import chuco.joel.challengeibk.domain.model.MovimientoModel
import chuco.joel.challengeibk.domain.utils.getColor

class MovementsAdapter(
    val context: Context
) : RecyclerView.Adapter<MovementsAdapter.MovementViewHolder>() {

    private val items = mutableListOf<MovimientoModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun bindItems(nItems: List<MovimientoModel>) {
        items.clear()
        items.addAll(nItems)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MovementViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementViewHolder {
        return MovementViewHolder(parent)
    }

    inner class MovementViewHolder(
        val parent: ViewGroup,
        val binding: ItemMovementBinding = ItemMovementBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MovimientoModel) {
            binding.apply {
                movimiento = data
                tvMountMovement.setTextColor(data.monto.getColor(context))
            }
        }

    }

}