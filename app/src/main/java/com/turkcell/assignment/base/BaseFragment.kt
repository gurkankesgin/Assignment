package com.turkcell.assignment.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.turkcell.assignment.util.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, V : BaseViewModel> : DaggerFragment(),LifecycleOwner {

    lateinit var mRootView: View
    lateinit var mViewModel: V
    var mViewDataBinding: B? = null

    @Inject
    lateinit var mViewModelFactory: ViewModelProviderFactory

    abstract fun getViewModelBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = provideViewModel()
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mRootView = mViewDataBinding!!.root
        return mRootView
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding?.setVariable(getViewModelBindingVariable(), mViewModel)
        mViewDataBinding?.lifecycleOwner = this
        mViewDataBinding?.executePendingBindings()
    }

    private fun provideViewModel(): V {
        val clazz: Class<V> = getViewModelClass(javaClass)
        mViewModel = ViewModelProvider(this, mViewModelFactory).get(clazz)
        return mViewModel
    }

    private fun getViewModelClass(aClass: Class<*>?): Class<V> {
        val type = aClass?.genericSuperclass
        return if (type is ParameterizedType) {
            type.actualTypeArguments[1] as Class<V>
        } else {
            getViewModelClass(aClass?.superclass)
        }
    }
}