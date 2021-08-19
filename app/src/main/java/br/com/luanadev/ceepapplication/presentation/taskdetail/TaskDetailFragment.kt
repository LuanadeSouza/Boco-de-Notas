package br.com.luanadev.ceepapplication.presentation.taskdetail

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.luanadev.ceepapplication.EventObserver
import br.com.luanadev.ceepapplication.R
import br.com.luanadev.ceepapplication.databinding.TaskdetailFragBinding
import br.com.luanadev.ceepapplication.presentation.task.DELETE_RESULT_OK
import br.com.luanadev.ceepapplication.util.getViewModelFactory
import br.com.luanadev.ceepapplication.util.setupRefreshLayout
import br.com.luanadev.ceepapplication.util.setupSnackbar
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar

class TaskDetailFragment : Fragment() {

    private val binding by viewBinding {
        TaskdetailFragBinding.inflate(layoutInflater)
    }
    private val args: TaskDetailFragmentArgs by navArgs()

    private val viewModel by viewModels<TaskDetailViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        viewModel.start(args.taskId)
        setHasOptionsMenu(true)
        setupFab()
        view?.setupSnackbar(this, viewModel.snackbarText, Snackbar.LENGTH_SHORT)
        setupNavigation()
        this.setupRefreshLayout(binding.refreshLayout)
    }

    private fun setupNavigation() {
        viewModel.deleteTaskEvent.value?.let {
            val action = TaskDetailFragmentDirections
                .actionTaskDetailFragmentToTasksFragment()
            findNavController().navigate(action)
        }

        viewModel.editTaskEvent.value?.let {
            val action = TaskDetailFragmentDirections
                .actionTaskDetailFragmentToAddEditTaskFragment(
                    args.taskId,
                    resources.getString(R.string.edit_task)
                )
            findNavController().navigate(action)
        }
    }

    private fun setupFab() {
        activity?.findViewById<View>(R.id.edit_task_fab)?.setOnClickListener {
            viewModel.editTask()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete -> {
                viewModel.deleteTask()
                true
            }
            else -> false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.taskdetail_fragment_menu, menu)
    }
}
