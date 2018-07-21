package unica.it.gestoreesami;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

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

                final EditText exam_name = (EditText)findViewById(R.id.examName);
                exams.add(new Exam(exam_name.getText().toString()));

                Bundle exam_info = new Bundle();
                exam_info.putParcelableArrayList("lista", exams);

                Intent update_exam = new Intent(getApplicationContext(), MainActivity.class);
                update_exam.putExtras(exam_info);

                startActivity(update_exam);
            }
        });



    }
}
