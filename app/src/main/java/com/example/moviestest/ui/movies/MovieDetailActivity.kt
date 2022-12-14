package com.example.moviestest.ui.movies

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.moviestest.common.Constants
import com.example.moviestest.databinding.ActivityMovieDetailBinding


class MovieDetailActivity : AppCompatActivity() {

    private val viewModel: MovieDetailViewModel by viewModels()
    private lateinit var binding: ActivityMovieDetailBinding
    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        bundle.let { movieId = it?.getInt(Constants.MOVIE_ID_KEY)!! }

        setContentView(binding.root)
        setUpUI()
        setUpObservers()
    }

    private fun setUpUI() {

//        movieItem.let {
//            Glide.with(binding.profileImage.context)
//                .load(it.profilePictureUrl)
//                .into(binding.profileImage)
//
//            binding.fullNameTextView.text = it.fullName
//            binding.nickNameTextView.text = it.nickName
//            binding.ageTextView.text = it.age
//            binding.profileTextView.text = getString(R.string.profileText, it.firstName);
//
//            binding.movieButton.visibility = View.VISIBLE
//            setMovieIconDrawableState(movieItem)
//
//            binding.movieButton.setOnClickListener { addRemoveMovie(movieItem) }
//        }

        setSupportActionBar(binding.toolbar)
        supportActionBar.apply {
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setDisplayShowHomeEnabled(true)
            title = ""
        }
    }


    private fun setUpObservers() {
        viewModel.getMovieDetail(movieId).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
//                    Status.SUCCESS -> {
//                        binding.container.visibility = View.VISIBLE
//                        resource.data?.let { data -> retrieveList(data) }
//                    }
//                    Status.ERROR -> {
//                        binding.container.visibility = View.VISIBLE
//                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                    }
//                    Status.LOADING -> {
//                        binding.container.visibility = View.GONE
//                    }
                }
            }
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

