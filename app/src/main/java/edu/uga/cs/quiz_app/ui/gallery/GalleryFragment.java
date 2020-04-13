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

    public TableRow getNewEntry(Context context, String user, String date, String score) {


        QuizHistoryTableRow row = new QuizHistoryTableRow(context, user, date, score);
        row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        return row;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        TableLayout table = root.findViewById(R.id.quiz_history_table);
        TableRow row = getNewEntry(getContext(), "user", "date", "score");
        table.addView(row, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        return root;
    }


}