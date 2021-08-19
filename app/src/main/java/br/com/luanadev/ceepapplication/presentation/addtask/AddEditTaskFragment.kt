package br.com.luanadev.ceepapplication.presentation.addtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.luanadev.ceepapplication.EventObserver
import br.com.luanadev.ceepapplication.databinding.AddtaskFragBinding
import br.com.luanadev.ceepapplication.presentation.task.ADD_EDIT_RESULT_OK
import br.com.luanadev.ceepapplication.util.getViewModelFactory
import br.com.luanadev.ceepapplication.util.setupRefreshLayout
import br.com.luanadev.ceepapplication.util.setupSnackbar
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar

class AddEditTaskFragment : Fragment() {

    private val binding by viewBinding {
        AddtaskFragBinding.inflate(layoutInflater)
    }

    private val args: AddEditTaskFragmentArgs by navArgs()

    private val viewModel by viewModels<AddEditTaskViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSnackbar()
        setupNavigation()
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        this.setupRefreshLayout(binding.refreshLayout)
        viewModel.start(args.taskId)
    }

    private fun setupSnackbar() {
        view?.setupSnackbar(this, viewModel.snackbarText, Snackbar.LENGTH_SHORT)
    }

    private fun setupNavigation() {
        viewModel.taskUpdatedEvent.observe(viewLifecycleOwner, EventObserver {
            val action = AddEditTaskFragmentDirections
                .actionAddEditTaskFragmentToTasksFragment()
            findNavController().navigate(action)
        })
    }
}
