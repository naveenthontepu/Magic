package com.example.naveen.magic;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;




public class RelationDialog extends DialogFragment {

    ImageView relationImage;
    EditText relationName;
    Bundle relationData;
    final static int IMAGE_FROM_GALLERY = 1;
    Uri imageUri;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View relation_dialog_view =inflater.inflate(R.layout.relation_dialog_view,null);
        relationImage = (ImageView)relation_dialog_view.findViewById(R.id.relationImage);
        relationName=(EditText)relation_dialog_view.findViewById(R.id.relationName);
        relationData = new Bundle();


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(relation_dialog_view);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "Cancel Button clicked", Toast.LENGTH_LONG).show();
                mListener.onDialogNegativeClick(RelationDialog.this);
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getActivity(), "OK Button clicked", Toast.LENGTH_LONG).show();
                relationData.putParcelable("relationImage", imageUri);
                String name;
                name = relationName.getText().toString();
                relationData.putString("relationName", name);
                mListener.onDialogPositiveClick(RelationDialog.this, relationData);
            }
        });
        relationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogImageClicked();
            }
        });

        return builder.create();
    }
    public void dialogImageClicked(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select the relation photo"), IMAGE_FROM_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1){
            if (requestCode == IMAGE_FROM_GALLERY){
                imageUri = data.getData();
                relationImage.setImageURI(imageUri);
            }
        }
    }
    public interface RelationDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog,Bundle extra);
        public void onDialogNegativeClick(DialogFragment dialog);
    }
    RelationDialogListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mListener = (RelationDialogListener) activity;
        }catch (Exception e){
            throw new ClassCastException(activity.toString()+" must implement Listener");
        }
    }
}
