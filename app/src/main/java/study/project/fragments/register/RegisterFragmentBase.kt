package study.project.fragments.register

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import study.project.viewmodels.RegisterViewModel

abstract class RegisterFragmentBase : Fragment() {

    val viewModel: RegisterViewModel by activityViewModels()

}