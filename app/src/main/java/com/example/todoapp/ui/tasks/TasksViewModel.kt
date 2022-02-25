package com.example.todoapp.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.todoapp.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*
* Hilt, ViewModel'i destekler ve bu nedenle ViewModels ile çalışmak hilt ile çok kolaydır.
* */

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskDao : TaskDao
) : ViewModel(){

    val tasks = taskDao.getTasks().asLiveData()

}