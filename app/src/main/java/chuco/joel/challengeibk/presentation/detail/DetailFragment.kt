package chuco.joel.challengeibk.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chuco.joel.challengeibk.R
import chuco.joel.challengeibk.databinding.FragmentDetailBinding
import chuco.joel.challengeibk.domain.model.CuentaModel
import chuco.joel.challengeibk.presentation.base.BaseFragment
import chuco.joel.challengeibk.presentation.detail.adapter.MovementsAdapter
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var cuentaArgs: CuentaModel
    val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cuentaArgs = args.cuenta
        setupViews()
        setupObservers()
        args.cuenta.id?.let { viewModel.load(it) }
    }

    private fun setupViews() {

        val toolbar = requireActivity().findViewById<MaterialToolbar>(R.id.topAppBar)
        val appBar = requireActivity().findViewById<AppBarLayout>(R.id.appBar)
        appBar.visibility = View.VISIBLE
        toolbar.title = "CONSULTAS"

        binding.apply {
            cuenta = cuentaArgs
            rvMovements.apply {
                viewModel._adapterMovements = MovementsAdapter(this.context)
                this.adapter = viewModel._adapterMovements
                this.layoutManager = GridLayoutManager(context, 1, RecyclerView.VERTICAL, false)
            }
            btnShared.setOnClickListener {
                val accountNumber = tvAccountNumber.text.toString()
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Mi número de cuenta es: $accountNumber")
                    type = "text/plain"
                }

                if (shareIntent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(Intent.createChooser(shareIntent, "Challenge IBK :: Compartir cuenta con..."))
                }
            }
        }
    }


    private fun setupObservers() {
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

        viewModel.isEmpty.observe(viewLifecycleOwner) {
            if (it) {
                binding.rvMovements.visibility = View.GONE
                binding.tvMessageWithoutMovements.visibility = View.VISIBLE
            } else {
                binding.rvMovements.visibility = View.VISIBLE
                binding.tvMessageWithoutMovements.visibility = View.GONE
            }
        }
    }


}