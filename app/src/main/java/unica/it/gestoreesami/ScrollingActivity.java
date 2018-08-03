package unica.it.gestoreesami;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ScrollingActivity extends AppCompatActivity {

    ArrayList<Exam> exams = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Controlla se ha ricevuto una lista di esami dall'intent che ha chiamato questa activity,
        //se si la salva su exams
        Bundle exam_list = getIntent().getExtras();
        if(exam_list!=null){
            exams = exam_list.getParcelableArrayList("lista");
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String exam_name = ((EditText)findViewById(R.id.examName)).getText().toString();
                final String page_number = ((EditText)findViewById(R.id.pageNumber)).getText().toString();
                int page = 0;
                if(!page_number.equals("")){
                    page = Integer.parseInt(page_number);
                }

                if(exam_name.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Inserire un nome per l'esame", Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    exams.add(new Exam(exam_name, page));
                    Bundle exam_info = new Bundle();
                    exam_info.putParcelableArrayList("lista", exams);
                    Intent update_exam = new Intent(getApplicationContext(), MainActivity.class);
                    update_exam.putExtras(exam_info);

                    startActivity(update_exam);
                }

            }
        });



    }
}
