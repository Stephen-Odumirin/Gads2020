package com.stdev.gads2020.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stdev.gads2020.GadsApi
import com.stdev.gads2020.LearningLeaders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LearningLeadersViewModel() : ViewModel() {

    //The internal Mutable Live Data String that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    val status : LiveData<String> get() = _status

    private val _leaderboard = MutableLiveData<List<LearningLeaders>>()
    val leaderboard : LiveData<List<LearningLeaders>> get() = _leaderboard

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getHoursLeaderboard()
    }

    private fun getHoursLeaderboard() {
        coroutineScope.launch {
            val getLeaderboardDeferred = GadsApi.retrofitService.getLearningHoursLeadersAsync()

            try {
                val listResult = getLeaderboardDeferred.await()
                //_status.value = "Success : ${listResult.size} Leaders Retrieved"
                if (listResult.isNotEmpty()){
                    _leaderboard.value = listResult
                }
            } catch (t: Throwable){
                _status.value = "Failure : ${t.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}