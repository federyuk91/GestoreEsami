package unica.it.gestoreesami;

import android.app.Activity;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;


public class ExamAdapter extends ArrayAdapter<Exam> {
    private int colorResourceId;

    public ExamAdapter(Activity context, ArrayList<Exam> exams, int colorResourceId) {

        super(context, 0, exams);
        this.colorResourceId = colorResourceId;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link currentExam} object located at this position in the list
        Exam currentExam = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView examName_TextView = listItemView.findViewById(R.id.examName_textView);
        // Get the version name from the current Exam object and set this text on the name TextView
        examName_TextView.setText(currentExam.getExamName());

        // Find the TextView in the list_item.xml layout with the ID examDate_textView
        TextView examDateTextView = listItemView.findViewById(R.id.examDate_textView);

        // Get the version number from the current AndroidFlavor object and set this text on the number TextView
        examDateTextView.setText(currentExam.getExamDate().toString());

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);

        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), colorResourceId);

        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView so that it can be shown in the ListView
        return listItemView;
    }



}




       /*Permette di accedere alla image view con l'icona delle notifiche
            ImageView imgID = listItemView.findViewById(R.id.image);
        */
