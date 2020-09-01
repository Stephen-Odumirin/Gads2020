package com.stdev.gads2020

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stdev.gads2020.databinding.FragmentLearningLeadersBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LearningLeadersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LearningLeadersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var leadersAdapter: LearningLeadersAdapter

    private val viewModel : LearningLeadersViewModel by lazy {
        ViewModelProvider(this).get(LearningLeadersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentLearningLeadersBinding.inflate(inflater)

        //allows data binding to observe livedata with the lifecycle of this fragmen
        binding.lifecycleOwner = this

        recyclerView = binding.learningRecyclerView
        leadersAdapter = LearningLeadersAdapter(context!!)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = leadersAdapter

        //giving the binding access to the Learning Leaders Fragment
        binding.learningLeadersViewModel = viewModel

        viewModel.leaderboard.observe(viewLifecycleOwner, Observer {
            leadersAdapter.setLeadersList(it)
        })

        return binding.root
        //return inflater.inflate(R.layout.fragment_learning_leaders, container, false)
    }
}