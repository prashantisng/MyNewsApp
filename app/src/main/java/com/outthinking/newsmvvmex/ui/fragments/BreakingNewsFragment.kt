package com.outthinking.newsmvvmex.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.outthinking.newsmvvmex.R
import com.outthinking.newsmvvmex.adapters.NewsAdapter
import com.outthinking.newsmvvmex.databinding.FragmentBreakingNewsBinding
import com.outthinking.newsmvvmex.ui.NewsActivity
import com.outthinking.newsmvvmex.ui.NewsViewModel
import com.outthinking.newsmvvmex.util.Resource

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BreakingNewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    private lateinit var binding:FragmentBreakingNewsBinding
    lateinit var newsAdapter: NewsAdapter
    private lateinit var viewModel:NewsViewModel
    val TAG="BreakingNewsFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentBreakingNewsBinding.inflate(inflater,container,false)
        Log.e("0","breakingnews framgnet")
        return binding.root
    }
    private fun setUpRecyclerView(){
        newsAdapter= NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter=newsAdapter
            layoutManager= LinearLayoutManager(activity)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as NewsActivity) .viewModel
        setUpRecyclerView()
        newsAdapter.setOnItemCLickListener {
                val bundle=Bundle().apply {
                    putSerializable("article",it)
                }
                findNavController().navigate(R.id.action_breakingNewsFragment_to_articleFragment,
                    bundle)
        }
        viewModel.breakingNews.observe(viewLifecycleOwner, Observer{response ->
                when(response){
                    is Resource.Success ->{
                        hideProgressBar()
                        response.data?.let { newsResponse ->  
                            newsAdapter.differ.submitList(newsResponse.articles)
                        }
                    }

                    is Resource.Error -> {
                        response.message?.let { message ->
                            Log.d(TAG,"An error occured:$message")
                        }
                    }

                    is Resource.Loading -> {
                        showProgressBar()
                    }
                }
        })
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility=View.INVISIBLE

    }
    private fun showProgressBar() {
        binding.paginationProgressBar.visibility=View.VISIBLE

    }
}