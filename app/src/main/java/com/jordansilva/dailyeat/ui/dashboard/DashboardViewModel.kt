package com.jordansilva.dailyeat.ui.dashboard

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import app.jordansilva.data.model.Recipe

/**
 * Created by jordansilva on 18/03/18.
 */
class DashBoardViewModel : ViewModel() {


    private lateinit var recipes : LiveData<Recipe>

}