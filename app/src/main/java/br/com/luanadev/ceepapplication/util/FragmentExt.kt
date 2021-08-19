package br.com.luanadev.ceepapplication.util

import androidx.fragment.app.Fragment
import br.com.luanadev.ceepapplication.TodoApplication
import br.com.luanadev.ceepapplication.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as TodoApplication).taskRepository
    return ViewModelFactory(repository, this)
}
