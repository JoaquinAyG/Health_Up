package study.project.fragments.register;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import study.project.databinding.FragmentPasswordBinding;

public class FragmentPasswordActivity extends Fragment {

    private FragmentPasswordBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentPasswordBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}