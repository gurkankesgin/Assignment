package com.turkcell.assignment.presentation.list

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.turkcell.assignment.R
import com.turkcell.assignment.base.BaseFragment
import com.turkcell.assignment.databinding.FragmentListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListFragment : BaseFragment<FragmentListBinding, ProductListViewModel>() {

    private var productListRVA: ProductListRVA? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindUI()
    }

    private fun initList() {
        productListRVA = ProductListRVA { product, imageView ->
            view?.let {
                val extras = FragmentNavigatorExtras(imageView to product.product_id)
                val action = ProductListFragmentDirections.navActionListToDetail(product)
                findNavController().navigate(action, extras)
            }

        }
        mViewDataBinding?.fragmentListRv?.apply {
            setHasFixedSize(true)
            adapter = productListRVA
            layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.VERTICAL)
        }
    }

    private fun bindUI() = CoroutineScope(Dispatchers.Main).launch {
        mViewModel.products.await().observe(viewLifecycleOwner, Observer {
            productListRVA?.updateList(it)
        })
    }

    override fun getViewModelBindingVariable() = BR.viewmodel

    override fun getLayoutId() = R.layout.fragment_list
}