package edu.uga.cs.quiz_app.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import edu.uga.cs.quiz_app.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public void addEntryToTable(TableLayout table, String user, String date, String score) {

        QuizHistoryTableRow row = new QuizHistoryTableRow(getContext(), user, date, score);
        table.addView(row, 5);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        TableLayout table = root.findViewById(R.id.quiz_history_table);
        addEntryToTable(table, "user", "date", "score");

        return root;
    }

}