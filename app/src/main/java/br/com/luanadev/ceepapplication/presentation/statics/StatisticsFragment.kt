package br.com.luanadev.ceepapplication.presentation.statics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.luanadev.ceepapplication.R
import br.com.luanadev.ceepapplication.databinding.AddtaskFragBinding
import br.com.luanadev.ceepapplication.databinding.StatisticsFragBinding
import br.com.luanadev.ceepapplication.util.getViewModelFactory
import br.com.luanadev.ceepapplication.util.setupRefreshLayout
import by.kirich1409.viewbindingdelegate.viewBinding

class StatisticsFragment : Fragment() {

    private val binding by viewBinding {
        StatisticsFragBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<StatisticsViewModel> { getViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        this.setupRefreshLayout(binding.refreshLayout)
    }
}
