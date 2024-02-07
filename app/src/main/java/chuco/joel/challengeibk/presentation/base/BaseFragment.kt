package chuco.joel.challengeibk.presentation.base

import androidx.fragment.app.Fragment
import chuco.joel.challengeibk.presentation.loading.LoadingDialog

abstract class BaseFragment : Fragment() {

    private var loading: LoadingDialog? = null
    private var isLoadingShow: Boolean = false

    protected fun showLoading(showLoading: Boolean) {
        if (showLoading && !isLoadingShow) {
            showLoadingDialog()
        } else {
            closeLoadingDialog()
        }
    }

    private fun showLoadingDialog() {
        removeLoadingDialogFromBackStack()
        if (activity != null) {
            if (loading == null) {
                loading = LoadingDialog()
                isLoadingShow = true
            }
            if (!loading!!.isAdded) {
                loading?.show(childFragmentManager, "loading")
            }
        }
    }

    private fun closeLoadingDialog() {
        if (loading != null) {
            loading?.dismiss()
            isLoadingShow = false
        }
    }

    private fun removeLoadingDialogFromBackStack() {
        if (activity != null) {
            val oldLoadingDialog = childFragmentManager.findFragmentByTag("loading")
            if (oldLoadingDialog != null) {
                if (!oldLoadingDialog.isVisible) {
                    childFragmentManager.beginTransaction()
                        .remove(oldLoadingDialog)
                        .addToBackStack(null)
                    isLoadingShow = false
                }
            }
        }
    }
}