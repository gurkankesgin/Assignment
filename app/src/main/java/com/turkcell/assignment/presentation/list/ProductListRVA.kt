package com.turkcell.assignment.presentation.list

import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.turkcell.assignment.R
import com.turkcell.assignment.data.db.entities.Product
import com.turkcell.assignment.databinding.ItemListProductBinding
import com.turkcell.assignment.util.extensions.inflate

class ProductListRVA(val clickItem: (product: Product, imageView: AppCompatImageView) -> Unit) :
    RecyclerView.Adapter<ProductListRVA.ProductListViewHolder>() {

    var productList = AsyncListDiffer(this, ProductDiffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(ItemListProductBinding.bind(parent.inflate(R.layout.item_list_product)))
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = productList.currentList[position]
        holder.bind(product)
        holder.binding.root.setOnClickListener {
            holder.binding.itemListProductIv.transitionName = product.product_id
            clickItem(product, holder.binding.itemListProductIv)
        }
    }

    override fun getItemCount() = productList.currentList.size

    fun updateList(productList: List<Product>) {
        this.productList.submitList(productList)
        notifyDataSetChanged()
    }

    inner class ProductListViewHolder constructor(val binding: ItemListProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            binding.product = product
        }
    }
}

object ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.product_id == newItem.product_id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}