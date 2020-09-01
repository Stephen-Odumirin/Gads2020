package com.stdev.gads2020

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stdev.gads2020.databinding.FragmentSkillLeadersBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SkillLeadersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SkillLeadersFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var skillLeadersAdapter: SkillLeadersAdapter

    private val viewModel : SkillLeadersViewModel by lazy {
        ViewModelProvider(this).get(SkillLeadersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSkillLeadersBinding.inflate(inflater)

        recyclerView = binding.skillRecyclerView
        skillLeadersAdapter = SkillLeadersAdapter(context!!)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = skillLeadersAdapter

        binding.lifecycleOwner = this

        binding.skillViewModel = viewModel

        viewModel.skillLeaderboard.observe(viewLifecycleOwner , Observer {
            skillLeadersAdapter.setSkillsList(it)
        })

        //skillLeadersAdapter.setSkillsList(viewModel.skillLeaderboard)

        return binding.root
        //return inflater.inflate(R.layout.fragment_skill_leaders, container, false)
    }
}