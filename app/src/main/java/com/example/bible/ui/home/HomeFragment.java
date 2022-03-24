package com.example.bible.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bible.Album1Activity;
import com.example.bible.Album2Activity;
import com.example.bible.Album3Activity;
import com.example.bible.Album4Activity;
import com.example.bible.BibleAudioActivity;
import com.example.bible.PodcastActivity;
import com.example.bible.R;
import com.example.bible.SermonsActivity;
import com.example.bible.databinding.FragmentHomeBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    public CardView music;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        CardView podcast = root.findViewById(R.id.podcast);
        music = root.findViewById(R.id.musicPlayer);
        CardView sermons = root.findViewById(R.id.sermons);
        CardView bible = root.findViewById(R.id.bibleAudio);

        PopupMenu popup = new PopupMenu(music.getContext(), music);

        podcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                podcast.getContext().startActivity(new Intent(podcast.getContext(), PodcastActivity.class));
            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
                bottomSheetDialog.setContentView(R.layout.activity_bottom_sheet_dialog);

                TextView album1 = bottomSheetDialog.findViewById(R.id.album1);
                TextView album2 = bottomSheetDialog.findViewById(R.id.album2);
                TextView album3 = bottomSheetDialog.findViewById(R.id.album3);
                TextView album4 = bottomSheetDialog.findViewById(R.id.album4);

                bottomSheetDialog.show();

                album1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        music.getContext().startActivity(new Intent(music.getContext(), Album1Activity.class));
                        bottomSheetDialog.dismiss();
                    }
                });

                album2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        music.getContext().startActivity(new Intent(music.getContext(), Album2Activity.class));
                        bottomSheetDialog.dismiss();
                    }
                });

                album3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        music.getContext().startActivity(new Intent(music.getContext(), Album3Activity.class));
                        bottomSheetDialog.dismiss();
                    }
                });

                album4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        music.getContext().startActivity(new Intent(music.getContext(), Album4Activity.class));
                        bottomSheetDialog.dismiss();
                    }
                });

            }
        });

        sermons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sermons.getContext().startActivity(new Intent(sermons.getContext(), SermonsActivity.class));
            }
        });

        bible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bible.getContext().startActivity(new Intent(bible.getContext(), BibleAudioActivity.class));
            }
        });

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}