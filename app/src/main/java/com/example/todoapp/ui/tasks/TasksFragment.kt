package com.example.todoapp.ui.tasks

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentTaskBinding
import com.example.todoapp.util.onQueryTextChanged
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

        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_tasks,menu)

        val searchItem=menu.findItem(R.id.action_search)
        val searchView=searchItem.actionView as SearchView

        searchView.onQueryTextChanged {
            //update search query
            viewModel.searchQuery.value=it


        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_sort_by_name -> {


                true
            }
            R.id.action_sort_by_date_created -> {

                true
            }
            R.id.action_hide_completed_tasks -> {


                true
            }
            else -> super.onOptionsItemSelected(item)


        }
    }

}