package com.example.login_formulario;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;

import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ListResumesActivity extends AppCompatActivity {

    private ArrayList<String> names;
    private ArrayList<String> emails;
    private ArrayList<String> positions;
    private ArrayList<String> resume_ids;
    private RecyclerView recyclerView;
    private AdapterDatos adapterDatos;

    private String undoName, undoEmail, undoPosition, undoResumeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_resumes);

        this.names = new ArrayList<>();
        this.emails = new ArrayList<>();
        this.positions = new ArrayList<>();
        this.resume_ids = new ArrayList<>();
        this.undoName = null;
        this.undoEmail = null;
        this.undoPosition = null;
        this.undoResumeID = null;

        this.recyclerView = findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        chargeList();
        this.adapterDatos = new AdapterDatos(this.names, this.emails, this.positions, this.resume_ids);
        this.recyclerView.setAdapter(adapterDatos);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(this.recyclerView);
    }

    private void chargeList(){
        for(Resume resume : Database.getInstance().getResumes()){
            this.names.add(resume.getFirstName() + " " + resume.getLastName());
            this.emails.add(resume.getEmailAddress());
            this.positions.add(resume.getPositionApplaying());
            this.resume_ids.add(resume.getID());
        }
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT |
            ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int index = viewHolder.getAdapterPosition();

            undoName = names.get(index);
            undoEmail = emails.get(index);
            undoPosition = positions.get(index);
            undoResumeID = resume_ids.get(index);

            switch (direction){
                case ItemTouchHelper.LEFT:
                    names.remove(index);
                    emails.remove(index);
                    positions.remove(index);
                    resume_ids.remove(index);
                    adapterDatos.notifyItemRemoved(index);

                    new AlertDialog.Builder(ListResumesActivity.this)
                            .setTitle("Delete resume")
                            .setMessage("Are you sure you want to delete this resume?")

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Database.getInstance().removeResume(undoResumeID);
                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    names.add(index, undoName);
                                    emails.add(index, undoEmail);
                                    positions.add(index, undoPosition);
                                    resume_ids.add(index, undoResumeID);
                                    adapterDatos.notifyItemInserted(index);
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                    break;

                case ItemTouchHelper.RIGHT:
                    Intent intent = new Intent(ListResumesActivity.this, ShowResumeActivity.class);
                    intent.putExtra("resume_id", resume_ids.get(index));
                    startActivity(intent);

                    names.remove(index);
                    emails.remove(index);
                    positions.remove(index);
                    resume_ids.remove(index);
                    adapterDatos.notifyItemRemoved(index);

                    names.add(index, undoName);
                    emails.add(index, undoEmail);
                    positions.add(index, undoPosition);
                    resume_ids.add(index, undoResumeID);
                    adapterDatos.notifyItemInserted(index);

                    break;

            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(ListResumesActivity.this, R.color.colorRed))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete_white_24dp)
                    .addSwipeRightBackgroundColor(ContextCompat.getColor(ListResumesActivity.this, R.color.colorGreen))
                    .addSwipeRightActionIcon(R.drawable.ic_visibility_white_24dp)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

}