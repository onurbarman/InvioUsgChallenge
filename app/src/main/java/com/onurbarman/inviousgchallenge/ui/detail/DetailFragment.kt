package com.onurbarman.inviousgchallenge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.onurbarman.inviousgchallenge.R
import com.onurbarman.inviousgchallenge.databinding.FragmentDetailBinding
import com.onurbarman.inviousgchallenge.utils.GlideUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private lateinit var binding: FragmentDetailBinding

    private val navArgs: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        navArgs.movie.run {
            binding.run {
                GlideUtils.urlToImageView(requireContext(),Poster,imgPoster)
                textMovieName.text = Title
                textGenre.text = Genre
                textPlot.text = Plot
                textImdb.text = imdbRating
            }
        }

    }

}