package edu.uga.cs.quiz_app.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import edu.uga.cs.quiz_app.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public TableLayout populateTable(TableLayout table) {
        // we will do this for each table entry
        String user = "user";
        String date = "date";
        String score = "score";

        QuizHistoryTableRow row = new QuizHistoryTableRow(getContext(), user, date, score);

        table.addView(row);

        return table;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        TableLayout table = root.findViewById(R.id.quiz_history_table);
        populateTable(table);

        return root;
    }
}