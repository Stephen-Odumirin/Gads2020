package com.stdev.gads2020.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stdev.gads2020.GadsApi
import com.stdev.gads2020.SkillIQ
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SkillLeadersViewModel : ViewModel() {

    //The internal Mutable Live Data String that stores the status of the most recent request
    private val _skillResponse = MutableLiveData<String>()
    val skillResponse : LiveData<String> get() = _skillResponse

    private val _skillLeaderboard = MutableLiveData<List<SkillIQ>>()
    val skillLeaderboard : LiveData<List<SkillIQ>> get() = _skillLeaderboard

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getSkillsLeaderboard()
    }

    private fun getSkillsLeaderboard() {

        coroutineScope.launch {
            val getSkillDeferred = GadsApi.retrofitService.getSkillsIqLeadersAsync()

            try {
                var listResult = getSkillDeferred.await()
                //_skillResponse.value = "Success : ${listResult.size} Skill Leaders Retrieved"

                if (listResult.isNotEmpty()){
                    _skillLeaderboard.value = listResult
                }
            } catch (t: Throwable){
                _skillResponse.value = "Failure : ${t.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}