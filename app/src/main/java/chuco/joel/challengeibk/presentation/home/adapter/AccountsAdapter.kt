package chuco.joel.challengeibk.presentation.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import chuco.joel.challengeibk.databinding.ItemAccountBinding
import chuco.joel.challengeibk.databinding.ItemEmptyBinding
import chuco.joel.challengeibk.domain.model.CuentaModel
import chuco.joel.challengeibk.domain.utils.getColor

class AccountsAdapter(
    val callback: (id: Int) -> Unit,
    val context: Context,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<CuentaModel>()
    private var isEmpty: Boolean = false

    @SuppressLint("NotifyDataSetChanged")
    fun bindItems(nItems: List<CuentaModel>) {
        if (nItems.isNotEmpty()) {
            isEmpty = nItems[0].id == 0
        }
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            isEmpty -> (holder as EmptyViewHolder).bind(items[position])
            else -> (holder as CuentaViewHolder).bind(items[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when {
            isEmpty -> EmptyViewHolder(parent)
            else -> CuentaViewHolder(parent)
        }
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

    inner class EmptyViewHolder(
        val parent: ViewGroup,
        val binding: ItemEmptyBinding= ItemEmptyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ),
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CuentaModel) {
            Log.i("card", "empty")
        }

    }
}