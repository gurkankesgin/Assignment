package com.turkcell.assignment.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.turkcell.assignment.R
import com.turkcell.assignment.base.BaseFragment
import com.turkcell.assignment.data.db.entities.Product
import com.turkcell.assignment.databinding.FragmentDetailBinding
import com.turkcell.assignment.util.enums.Status
import com.turkcell.assignment.util.enums.ViewState
import com.turkcell.assignment.util.extensions.showMessage
import kotlinx.coroutines.*

class ProductDetailFragment : BaseFragment<FragmentDetailBinding, ProductDetailViewModel>() {

    private val argsProductDetail: ProductDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = argsProductDetail.product
        mViewModel.product.postValue(product)
        setTransition(product)
        bindUI(product.product_id)
    }


    private fun bindUI(id: String) = lifecycleScope.launch {
        val resource = mViewModel.getProduct(id)
        when (resource.status) {
            Status.SUCCESS -> {
                mViewModel.product.postValue(resource.data)
                mViewModel.viewState.set(ViewState.CONTENT)
            }
            Status.ERROR -> {
                showMessage(resource.message)
                mViewModel.viewState.set(ViewState.ERROR)
            }
        }
    }

    private fun setTransition(product: Product) {
        mViewDataBinding?.fragmentListIv?.transitionName = product.product_id
        postponeEnterTransition()
        mRootView.viewTreeObserver.addOnPreDrawListener {
            startPostponedEnterTransition()
            true
        }
    }

    override fun getViewModelBindingVariable() = BR.viewmodel

    override fun getLayoutId() = R.layout.fragment_detail
}