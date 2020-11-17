package com.example.fmoviles;

import android.os.Bundle;

import com.example.fmoviles.adapters.MovileAdapter;
import com.example.fmoviles.models.MovileModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends BaseActivity {



    private FloatingActionButton fab_create_list;

    ListView lv_list_moviles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        super.init();
        init();


        fab_create_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCreate();
            }
        });

        lv_list_moviles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                model= modelArrayList.get(position);
                goToDetail(model);
            }
        });

    }

    protected void init(){
        fab_create_list = findViewById(R.id.fab_create_list);
        lv_list_moviles = findViewById(R.id.lv_list_moviles);
    }

    protected void getMoviles(){
        if(collectionReference != null){
    collectionReference.get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                   if(task.getResult() != null){
                    modelArrayList = new ArrayList<>();
                    for(QueryDocumentSnapshot snapshot : task.getResult()){
                    model = snapshot.toObject(MovileModel.class);
                    modelArrayList.add(model);
                    }
                    if(modelArrayList.size() > 0){
                    paintMoviles(modelArrayList);
                    }else{
                        makeSimpleAlertDialog("Info","No hay dispositivos registrados, toca el boton agregar");
                    }
                   }else{
                   makeSimpleAlertDialog("Warning","No moviles found");
                   }
                    }else{
                    makeSimpleAlertDialog("Error",task.getException().getMessage());
                    }
                }
            });
        }else{
        makeSimpleToast("Error DB",1);
        }
    }

    protected void  paintMoviles(ArrayList<MovileModel> modelArrayList){
        adapter = new MovileAdapter(this,modelArrayList);
        lv_list_moviles.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMoviles();
    }
}