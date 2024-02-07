package chuco.joel.challengeibk.presentation.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import chuco.joel.challengeibk.databinding.ItemAccountBinding
import chuco.joel.challengeibk.domain.model.CuentaModel
import chuco.joel.challengeibk.domain.utils.getColor

class AccountsAdapter(
    val callback: (id: Int) -> Unit,
    val context: Context,
) : RecyclerView.Adapter<AccountsAdapter.CuentaViewHolder>() {

    private val items = mutableListOf<CuentaModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun bindItems(nItems: List<CuentaModel>) {
        items.clear()
        items.addAll(nItems)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindNewItem(item: CuentaModel) {
        items.add(item)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun bindNewItems(nItems: List<CuentaModel>) {
        nItems.forEach {
            items.add(it)
        }
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CuentaViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentaViewHolder {
        return CuentaViewHolder(parent)
    }

    inner class CuentaViewHolder(
        val parent: ViewGroup,
        val binding: ItemAccountBinding= ItemAccountBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CuentaModel) {
            binding.apply {
                cuenta = data
                tvSaldo.setTextColor(data.saldo.getColor(context))
                cvCuenta.setOnClickListener {
                    callback(data.id ?: 0)
                }
            }
        }

    }
}