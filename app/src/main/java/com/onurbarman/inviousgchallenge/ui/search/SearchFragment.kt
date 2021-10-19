package com.onurbarman.inviousgchallenge.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.onurbarman.inviousgchallenge.R
import com.onurbarman.inviousgchallenge.data.model.movie.MovieResponse
import com.onurbarman.inviousgchallenge.databinding.FragmentSearchBinding
import com.onurbarman.inviousgchallenge.ui.custom.LoadingDialog
import com.onurbarman.inviousgchallenge.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel by viewModels()

    private val searchAdapter: SearchAdapter by lazy {
        SearchAdapter(listOf()) { movie -> onMovieClick(movie) }
    }

    private val loadingDialog: LoadingDialog by lazy {
        LoadingDialog()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initClick()
        observeData()
    }

    private fun initView() {
        binding.run {
            recyclerViewMovie.run {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
                adapter=searchAdapter
            }
        }
    }

    private fun initClick() {
        binding.run {
            btnSearch.setOnClickListener {
                if (Utils.isNetworkAvailable(requireContext())) {
                    editSearch.text?.let {
                        if (it.isNotEmpty()) {
                            loadingDialog.show(this@SearchFragment.childFragmentManager,"Loading")
                            viewModel.getMovie(it.toString())
                        }
                    }
                } else {
                    Utils.showToast(requireContext(),getString(R.string.no_internet))
                }
            }
        }
    }

    private fun observeData() {
        viewModel.postMovie.observe(viewLifecycleOwner, {
            loadingDialog.dismiss()
            if (it != null){
                if (it.Response == "True") {
                    searchAdapter.updateMovies(listOf(it))
                }
                else{
                    Utils.showToast(requireContext(),it.Error ?: getString(R.string.error_occured))
                }
            } else {
                Utils.showToast(requireContext(),getString(R.string.error_occured))
            }
        })
    }

    private fun onMovieClick(movie: MovieResponse) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(movie)
        )
    }

}