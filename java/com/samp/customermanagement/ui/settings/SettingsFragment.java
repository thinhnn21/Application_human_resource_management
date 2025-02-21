package com.samp.customermanagement.ui.settings;

import static android.content.Context.MODE_PRIVATE;

import static com.samp.customermanagement.utils.Contants.KEY_HIDE_ADDRESS;
import static com.samp.customermanagement.utils.Contants.KEY_HIDE_AGE;
import static com.samp.customermanagement.utils.Contants.KEY_HIDE_REMOVE;
import static com.samp.customermanagement.utils.Contants.KEY_HIDE_SALARY;
import static com.samp.customermanagement.utils.Contants.KEY_PLAY_MUSIC;
import static com.samp.customermanagement.utils.Contants.PREFS_NAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.samp.customermanagement.MainActivity;
import com.samp.customermanagement.core.services.BackgroundMusicService;
import com.samp.customermanagement.databinding.FragmentSettingsBinding;
import com.samp.customermanagement.ui.customer.CustomerViewModel;

public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel settingsViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch musicSwitch = binding.musicSwitch;
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFS_NAME , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        musicSwitch.setChecked(sharedPreferences.getBoolean(KEY_PLAY_MUSIC, true));
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean(KEY_PLAY_MUSIC, isChecked);
                editor.apply();

                Intent musicServiceIntent = new Intent(getActivity(), BackgroundMusicService.class); // Use activity context
                if (isChecked) {
                    getActivity().startService(musicServiceIntent);
                } else {
                    getActivity().stopService(musicServiceIntent);
                }
            }
        });
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchHideAge = binding.switchHideAge;
        switchHideAge.setChecked(sharedPreferences.getBoolean(KEY_HIDE_AGE, false));
        switchHideAge.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean(KEY_HIDE_AGE, isChecked);
            editor.apply();
            Intent intent = new Intent("com.example.customermanagement.UPDATE_UI");
            getActivity().sendBroadcast(intent);
        });

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchHideSalary = binding.switchHideSalary;
        switchHideSalary.setChecked(sharedPreferences.getBoolean(KEY_HIDE_SALARY, false));
        switchHideSalary.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean(KEY_HIDE_SALARY, isChecked);
            editor.apply();
            Intent intent = new Intent("com.example.customermanagement.UPDATE_UI");
            getActivity().sendBroadcast(intent);
        });

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchHideAddress = binding.switchHideAddress;
        switchHideAddress.setChecked(sharedPreferences.getBoolean(KEY_HIDE_ADDRESS, false));
        switchHideAddress.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean(KEY_HIDE_ADDRESS, isChecked);
            editor.apply();
            Intent intent = new Intent("com.example.customermanagement.UPDATE_UI");
            getActivity().sendBroadcast(intent);
        });

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switchHideRemove = binding.switchHideRemove;
        switchHideRemove.setChecked(sharedPreferences.getBoolean(KEY_HIDE_REMOVE, false));
        switchHideRemove.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean(KEY_HIDE_REMOVE, isChecked);
            editor.apply();
            CustomerViewModel customerViewModel = new ViewModelProvider(requireActivity()).get(CustomerViewModel.class);
            boolean hasRemove = sharedPreferences.getBoolean(KEY_HIDE_REMOVE, false);
            customerViewModel.loadAllCustomers(hasRemove);
            Intent intent = new Intent("com.example.customermanagement.UPDATE_UI");
            getActivity().sendBroadcast(intent);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}