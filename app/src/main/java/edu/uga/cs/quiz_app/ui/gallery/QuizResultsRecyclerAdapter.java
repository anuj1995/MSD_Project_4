package edu.uga.cs.quiz_app.ui.gallery;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.uga.cs.quiz_app.R;
import edu.uga.cs.quiz_app.datamodel.QuizResults;

public class QuizResultsRecyclerAdapter extends RecyclerView.Adapter<QuizResultsRecyclerAdapter.QuizResultHolder> {

    public static final String DEBUG_TAG = "JobLeadRecyclerAdapter";

    private List<QuizResults> quizResults;

    public QuizResultsRecyclerAdapter( List<QuizResults> resultsList ) {
        this.quizResults = resultsList;
    }

    // The adapter must have a ViewHolder class to "hold" one item to show.
    class QuizResultHolder extends RecyclerView.ViewHolder {

        TextView user;
        TextView date;
        TextView result;

        public QuizResultHolder(View itemView ) {
            super(itemView);

            user = (TextView) itemView.findViewById( R.id.user);
            date = (TextView) itemView.findViewById( R.id.date);
            result = (TextView) itemView.findViewById( R.id.result);
        }
    }

    @Override
    public QuizResultHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        // We need to make sure that all CardViews have the same, full width, allowed by the parent view.
        // This is a bit tricky, and we must provide the parent reference (the second param of inflate)
        // and false as the third parameter (don't attach to root).
        // Consequently, the parent view's (the RecyclerView) width will be used (match_parent).
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.quiz_result, parent, false );
        return new QuizResultHolder( view );
    }

    // This method fills in the values of a holder to show a JobLead.
    // The position parameter indicates the position on the list of jobs list.
    @Override
    public void onBindViewHolder( QuizResultHolder holder, int position ) {
        QuizResults singleResult = quizResults.get( position );

        Log.d( DEBUG_TAG, "onBindViewHolder: " + singleResult );

        holder.user.setText( singleResult.getUsername());
        holder.date.setText( singleResult.getDate() );
        holder.result.setText( singleResult.getResult() );
    }

    @Override
    public int getItemCount() {
        return quizResults.size();
    }
}

