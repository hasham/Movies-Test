package com.example.moviestest.ui.movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviestest.common.Status
import com.example.moviestest.data.models.Movie
import com.example.moviestest.data.models.MovieListResponse
import com.example.moviestest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity(), MoviesListAdapter.OnItemClickListener {

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var moviesListAdapter: MoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setUpObservers()
    }

    private fun setUpUI() {
        val divider = DividerItemDecoration(this@MovieActivity, LinearLayout.VERTICAL)
        moviesListAdapter = MoviesListAdapter(this@MovieActivity)
        binding.recentMoviesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MovieActivity)
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = moviesListAdapter
            addItemDecoration(divider)
        }
       
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObservers() {
        viewModel.getMoviesList().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.container.visibility = View.VISIBLE
                        resource.data?.let { data -> retrieveList(data) }
                    }
                    Status.ERROR -> {
                        binding.container.visibility = View.VISIBLE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.container.visibility = View.GONE
                    }
                }
            }
        })
    }


    private fun retrieveList(movies: MovieListResponse) {

      

        moviesListAdapter.apply {
            moviesListAdapter.submitList(movies.results)
        }
    }

    override fun onItemClick(item: Movie) {

//        val intent = Intent(this@MovieActivity, FriendDetailActivity::class.java)
//        intent.putExtra(Constants.FRIENDS, item)
//        startActivity(intent)
    }
}