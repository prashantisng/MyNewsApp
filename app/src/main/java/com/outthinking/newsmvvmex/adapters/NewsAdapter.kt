package com.outthinking.newsmvvmex.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.outthinking.newsmvvmex.R
import com.outthinking.newsmvvmex.databinding.ItemArticlePreviewBinding
import com.outthinking.newsmvvmex.models.Article

class NewsAdapter  :RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){

    inner class ArticleViewHolder(private val binding: ItemArticlePreviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.apply {
                Glide.with(itemView)
                    .load(article.urlToImage)
                    .into(ivArticleImage)
                tvSource.text = article.source.name
                tvTitle.text = article.title
                tvDescription.text = article.description
                tvPublishedAt.text = article.publishedAt

                // Correct way to set click listener on the item
                itemView.setOnClickListener {
                    OnItemClickListener?.let { click ->
                        click(article)
                    }
                }
            }
        }
    }

    private val differCallback=object:DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url==newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): NewsAdapter.ArticleViewHolder {
      //  return  ArticleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_article_preview,parent,false))
        val binding=ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ArticleViewHolder, position: Int) {
        val article=differ.currentList[position]
      /*  holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into()

        }*/
        holder.bind(article)
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }
    private var OnItemClickListener :((Article) ->Unit)?=null
    fun setOnItemCLickListener(listener:(Article) ->Unit){
        OnItemClickListener=listener
    }
}