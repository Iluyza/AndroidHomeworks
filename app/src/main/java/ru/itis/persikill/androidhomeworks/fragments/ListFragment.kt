package ru.itis.persikill.androidhomeworks.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import ru.itis.persikill.androidhomeworks.R
import ru.itis.persikill.androidhomeworks.database.TaskDatabase
import ru.itis.persikill.androidhomeworks.databinding.FragmentListBinding
import ru.itis.persikill.androidhomeworks.enteties.Task
import ru.itis.persikill.androidhomeworks.listadapter.ItemConstants.EXTRA_TASK_ID
import ru.itis.persikill.androidhomeworks.listadapter.TaskListAdapter

class ListFragment : androidx.fragment.app.Fragment(R.layout.fragment_list) {
    private lateinit var binding: FragmentListBinding
    private lateinit var database: TaskDatabase
    private var counter: Int = 0
    private var taskListAdapter: TaskListAdapter? = null

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        database = TaskDatabase.getInstance(this.requireContext())

        taskListAdapter = TaskListAdapter({ navigateToFragment(it) }, { deleteItemById(it) })

        with(binding) {
            toolbar.setOnMenuItemClickListener {
                onOptionsItemSelected(it)
            }

            fabtnAdd.setOnClickListener {
                navigateToFragment(null)
            }

            rvTasks.run {
                adapter = taskListAdapter
                addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
            }
        }

        scope.launch {
            val list = getListFromDatabase()

            if (list.isEmpty()) {
                binding.tvStart.visibility = View.VISIBLE
                binding.rvTasks.visibility = View.GONE
            } else {
                updateTaskList(list)
            }
        }
    }

    private suspend fun getListFromDatabase(): List<Task> {
        val listDeferred: Deferred<List<Task>> = scope.async {
            withContext(Dispatchers.IO) {
                database.taskDao().findAllTasks()
            }
        }

        return listDeferred.await()
    }

    private fun updateTaskList(newList: List<Task>) {
        with(binding) {
            if (newList.isEmpty()) {
                tvStart.visibility = View.VISIBLE
                rvTasks.visibility = View.GONE
            } else {
                tvStart.visibility = View.GONE
                rvTasks.visibility = View.VISIBLE
            }
        }
        taskListAdapter?.submitList(ArrayList(newList))
    }

    private fun navigateToFragment(id: Int?) {
        var bundle: Bundle? = null
        id?.let {
            bundle = Bundle().apply {
                putInt(EXTRA_TASK_ID, id)
            }
        }
        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .build()

        findNavController().navigate(
            R.id.action_listFragment_to_taskFragment,
            bundle,
            options
        )
    }

    private fun deleteItemById(id: Int) {
        scope.launch {
            withContext(Dispatchers.IO) {
                database.taskDao().deleteTaskById(id)
            }
            updateTaskList(getListFromDatabase())
            showMessage("Элемент $id был удалён из списка")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_delete -> {
                scope.launch {
                    if (checkDatabaseEmpty()) {
                        showMessage(getString(R.string.no_task_saved))
                    } else {
                        AlertDialog.Builder(requireContext()) // context??
                            .setMessage(R.string.notif_delete_all_tasks)
                            .setPositiveButton(R.string.yes) { dialog, _ ->
                                scope.launch {
                                    deleteAllTasks()
                                }
                                updateTaskList(emptyList())
                                dialog.dismiss()
                            }
                            .setNegativeButton(R.string.no) { dialog, _ ->
                                dialog.dismiss()
                            }
                            .show()
                    }
                }
            }
            R.id.menu_item_change -> {
                changeTheme()
                if (counter % 2 == 0) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }
        return true
    }

    private fun changeTheme() {
        counter++
    }

    private suspend fun deleteAllTasks() {
        withContext(Dispatchers.IO) {
            database.taskDao().deleteAll()
        }
    }

    private suspend fun checkDatabaseEmpty(): Boolean {
        val list = getListFromDatabase()
        return list.isEmpty()
    }

    private fun showMessage(message: String) {
        Snackbar.make(
            requireActivity().findViewById(R.id.fragment_container),
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scope.cancel()
    }
}