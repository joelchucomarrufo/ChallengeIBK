package chuco.joel.challengeibk.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import chuco.joel.challengeibk.R
import chuco.joel.challengeibk.databinding.FragmentLoginBinding
import chuco.joel.challengeibk.presentation.base.BaseFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        (activity as AppCompatActivity).supportActionBar?.hide()
        setupViews(binding)
        setupObservers()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    private fun setupViews(binding: FragmentLoginBinding) {

        val toolbar = requireActivity().findViewById<Toolbar>(R.id.topAppBar)
        toolbar.visibility = View.GONE
    }

    private fun setupObservers() {
        viewModel.message.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                findNavController().navigate(R.id.actionLoginFragmentToHomeFragment)
            }
        }

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
    }

}