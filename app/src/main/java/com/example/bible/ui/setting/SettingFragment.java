package com.example.bible.ui.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bible.R;
import com.example.bible.SettingPreferences;
import com.example.bible.SplashScreenActivity;
import com.example.bible.databinding.FragmentSettingBinding;
import com.google.android.material.card.MaterialCardView;

public class SettingFragment extends Fragment {

    private SettingViewModel settingViewModel;
    private FragmentSettingBinding binding;
    private MaterialCardView cardView;
    private ImageButton show;
    private LinearLayout hiddenLayout;
    private AppCompatCheckBox notifyBible;
    private AppCompatCheckBox notifyPrayer;
    private RadioGroup rgLanguage;
    private RadioButton rbEnglish;
    private RadioButton rbRussian;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingViewModel =
                new ViewModelProvider(this).get(SettingViewModel.class);

        binding = FragmentSettingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cardView = root.findViewById(R.id.cardView);
        show = root.findViewById(R.id.button_show);
        hiddenLayout = root.findViewById(R.id.linear_layout);
        notifyBible = root.findViewById(R.id.notifBible);
        notifyPrayer = root.findViewById(R.id.prayerRequest);
        rgLanguage = root.findViewById(R.id.rgLanguage);
        rbEnglish = root.findViewById(R.id.radio_eng);
        rbRussian = root.findViewById(R.id.radio_rus);

        setDataSetting();

        notifyBible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SettingPreferences.setNotifyBible(getActivity().getBaseContext(), b);
                showAlert();
            }
        });

        notifyPrayer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SettingPreferences.setNotifyPrayer(getActivity().getBaseContext(), b);
                showAlert();
            }
        });

        rgLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                setLanguage();
            }
        });

        show.setOnClickListener(v -> {
            if (hiddenLayout.getVisibility() == root.VISIBLE) {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenLayout.setVisibility(root.GONE);
                show.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
            } else {
                TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                hiddenLayout.setVisibility(root.VISIBLE);
                show.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
            }
        });


        settingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    private void setLanguage() {
        int selectedId = rgLanguage.getCheckedRadioButtonId();
        String codeLocal = "en";

        if (selectedId == rbRussian.getId()) {
            codeLocal = "ba";
        }

        SettingPreferences.setCodeLanguage(getActivity().getBaseContext(), codeLocal);
        showAlert();
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getResources().getString(R.string.please_restart));
        builder.setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent landing = new Intent(getActivity().getBaseContext(), SplashScreenActivity.class);
                startActivity(landing);
                getActivity().finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void setDataSetting() {
        boolean isNotifyBible = SettingPreferences.getNotifyBible(getActivity().getBaseContext());
        boolean isNotifyPrayer = SettingPreferences.getNotifyPrayer(getActivity().getBaseContext());
        String codeLang = SettingPreferences.getCodeLanguage(getActivity().getBaseContext());

        // Notify Bible
        if (isNotifyBible) {
            notifyBible.setChecked(true);
        }

        // Notify Prayer
        if (isNotifyPrayer) {
            notifyPrayer.setChecked(true);
        }

        // Language
        if (codeLang.equals("en")) {
            rbEnglish.setChecked(true);
        }

        if (codeLang.equals("ba")) {
            rbRussian.setChecked(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}