package chuco.joel.challengeibk.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chuco.joel.challengeibk.R
import chuco.joel.challengeibk.databinding.FragmentHomeBinding
import chuco.joel.challengeibk.presentation.base.BaseFragment
import chuco.joel.challengeibk.presentation.home.adapter.AccountsAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var originalMarginTop: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        setupViews(binding)
        setupObservers(binding)
        return binding.root
    }

    private fun setupViews(binding: FragmentHomeBinding) {

        val toolbar = requireActivity().findViewById<MaterialToolbar>(R.id.topAppBar)
        val appBar = requireActivity().findViewById<AppBarLayout>(R.id.appBar)
        appBar.visibility = View.VISIBLE
        toolbar.title = "PRODUCTOS"

        binding.apply {
            rvAccountList.apply {
                viewModel?._adapterAccounts = AccountsAdapter(::showCuentaDetail, this.context)
                this.adapter = viewModel?._adapterAccounts
                this.layoutManager = GridLayoutManager(context, 1, RecyclerView.VERTICAL, false)
            }
            srAccounts.setOnRefreshListener {
                viewModel?.update()
            }
        }
    }

    private fun setupObservers(binding: FragmentHomeBinding) {
        viewModel.error.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(resources.getString(R.string.app_name))
                    .setMessage(it)
                    .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        viewModel.loadingPull.observe((viewLifecycleOwner)) {
            if (!it) {
                binding.srAccounts.isRefreshing = false
            }
        }
    }

    private fun showCuentaDetail(id: Int) {
        /*PokemonDetailFragment(pokemon, ::reloadPokemonList, backgroundColorSelected, initialsColorSelected).show(
            parentFragmentManager,
            "DialogPokemonDetail"
        )*/
    }

}