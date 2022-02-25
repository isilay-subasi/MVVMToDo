package com.example.todoapp.ui.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint


/*
* Hilt, yalnızca @AndroidEntryPoint ile açıklamalı sınıflara bağımlılık sağlar.
*
* */

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_task) {

    /*Basitçe bir ViewModel talep edemeyeceğinizi unutmayın. ViewModelProvider API'sini kullanarak ViewModel'i oluştururken.
     *Parçanız/aktiviteniz içinde bir ViewModel istemek için aşağıdaki kodu kullanın.
     * */
    private val viewModel : TasksViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTaskBinding.bind(view)

        val taskAdapter = TasksAdapter()

        binding.apply {
            recyclerViewTasks.apply {
                adapter=taskAdapter
                layoutManager=LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.tasks.observe(viewLifecycleOwner){
            taskAdapter.submitList(it)
        }

    }

}