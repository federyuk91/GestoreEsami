package unica.it.gestoreesami;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    unica.it.gestoreesami.ExamAdapter examsAdapter;
    ListView listView;
    Bundle exam_list = new Bundle();
    ArrayList<Exam> exams = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get the extras from the intent that started this activity
        Bundle exam_info = getIntent().getExtras();

        if(exam_info!=null){
            if(exam_info.getParcelableArrayList("lista")!=null){
                exams = exam_info.getParcelableArrayList("lista");
            }

        }
        exam_list.putParcelableArrayList("lista", exams);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addExam);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent insert_exam = new Intent(getApplicationContext(), ScrollingActivity.class);

                insert_exam.putExtras(exam_list);
                startActivity(insert_exam);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //exams.add(new Exam("Programmazione 1"));

        listView =  findViewById(R.id.list);
        examsAdapter = new unica.it.gestoreesami.ExamAdapter(this, exams, R.color.exam_bg);
        listView.setAdapter(examsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link Exam} object at the given position the user clicked on
                Exam exam = exams.get(position);
                //Toast.makeText(getApplicationContext(), "Toast"+exam.getExamName(), Toast.LENGTH_LONG).show();
                Intent goToProgressPage = new Intent(getApplicationContext(), ExamProgress.class);;
                Bundle sendExamInfo = new Bundle();
                sendExamInfo.putString("title", exam.getExamName());
                sendExamInfo.putString("date", exam.getExamDate().toString());
                sendExamInfo.putInt("pages", exam.getPageNumber());
                sendExamInfo.putParcelableArrayList("lista", exams);
                goToProgressPage.putExtras(sendExamInfo);
                startActivity(goToProgressPage);
            }
        });
    }

    /*IMPORTANTE*/
    //Le funzioni che seguono sono state inserite automaticamente dall'activity scelta, sono ancora tutte da vedere
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
