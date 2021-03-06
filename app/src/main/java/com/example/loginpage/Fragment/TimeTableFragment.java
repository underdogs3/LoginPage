









package com.example.loginpage.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.loginpage.HomePage;
import com.example.loginpage.MainActivity;
import com.example.loginpage.MyAdapter1;
import com.example.loginpage.R;
import com.example.loginpage.Monday;
import com.example.loginpage.SaveSharedPreference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class TimeTableFragment extends Fragment {
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;



    String userId;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference reference = firebaseDatabase.getReference();


    private Uri uri;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_time_table, container, false);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();



        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imageRef = storageReference.child("timetables/" + userId + "/timetableSem.png");


        try {
            final File localFile = File.createTempFile("timetable", "png");

            imageRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getActivity(), "Time Table", Toast.LENGTH_SHORT).show();
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            ((ImageView) view.findViewById(R.id.timetablepic)).setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener((new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "error " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }));
        }catch (IOException e){
            e.printStackTrace();
        }




        /*Monday_Lectures*/
//        ListView listViewMon = view.findViewById(R.id.timetable_listview_mon);
//
//        Monday  first = new Monday("AppliedMathematics III","Abhiruchi Dakwale ","Theory","10:30 AM","11:30 AM","205");
//        Monday  second= new Monday("Data Structure","Jayant Sawarkar ","Theory","11:30 AM","12:30 PM","206");
//        Monday  third = new Monday("Digital Logic & Computer Organization and Architecture","ssk ","Theory","1:00 PM","2:00 PM","302");
//        Monday  fourth = new Monday("Computer Graphics","prasant Itankar ","Theory","2:00 PM","3:00 PM","206");
//        Monday  fifth = new Monday("OOPM","Shreya Patankar ","practical","3:00 AM","4:00 PM","101");
//
//        ArrayList<Monday> lecturesOnMonday = new ArrayList<>();
//        lecturesOnMonday.add(first);
//        lecturesOnMonday.add(second);
//        lecturesOnMonday.add(third);
//        lecturesOnMonday.add(fourth);
//        lecturesOnMonday.add(fifth);
//        MyAdapter1 adapterMon = new MyAdapter1(getContext(),R.layout.timetable_row,lecturesOnMonday);
//        listViewMon.setAdapter(adapterMon);
//
////                        /*Tuesday_Lectures*/
//        ListView listViewTue = view.findViewById(R.id.timetable_listview_tue);
////
//        Monday  Tuefirst = new Monday("AppliedMathematics III","Abhiruchi Dakwale ","Theory","10:30 AM","11:30 AM","205");
//        Monday  Tuesecond= new Monday("Data Structure","Jayant Sawarkar ","Theory","11:30 AM","12:30 PM","206");
//        Monday  Tuethird = new Monday("Digital Logic & Computer Organization and Architecture","ssk ","Theory","1:00 PM","2:00 PM","302");
//        Monday  Tuefourth = new Monday("Computer Graphics","prasant Itankar ","Theory","2:00 PM","3:00 PM","206");
//        Monday  Tuefifth = new Monday("OOPM","Shreya Patankar ","practical","3:00 AM","4:00 PM","101");
//
//        ArrayList<Monday> lecturesOnTuesday = new ArrayList<>();
//        lecturesOnTuesday.add(Tuefirst);
//        lecturesOnTuesday.add(Tuesecond);
//        lecturesOnTuesday.add(Tuethird);
//        lecturesOnTuesday.add(Tuefourth);
//        lecturesOnTuesday.add(Tuefifth);
//        MyAdapter1 adapterTue = new MyAdapter1(getContext(),R.layout.timetable_row,lecturesOnTuesday);
//        listViewTue.setAdapter(adapterTue);
////
////                         /*Wednesday_Lectures*/
//        ListView listViewWed = view.findViewById(R.id.timetable_listview_wed);
////
//        Monday  Wedfirst = new Monday("Applied Mathematics III","Abhiruchi Dakwale ","Theory","10:30 AM","11:30 AM","205");
//        Monday  Wedsecond= new Monday("Data Structure","Jayant Sawarkar ","Theory","11:30 AM","12:30 PM","206");
//        Monday  Wedthird = new Monday("Digital Logic & Computer Organization and Architecture","ssk ","Theory","1:00 PM","2:00 PM","302");
//        Monday  Wedfourth = new Monday("Computer Graphics","prasant Itankar ","Theory","2:00 PM","3:00 PM","206");
//        Monday  Wedfifth = new Monday("OOPM","Shreya Patankar ","practical","3:00 AM","4:00 PM","101");
//
//       ArrayList<Monday> lecturesOnWednesday = new ArrayList<>();
//        lecturesOnWednesday.add(Wedfirst);
//        lecturesOnWednesday.add(Wedsecond);
//        lecturesOnWednesday.add(Wedthird);
//        lecturesOnWednesday.add(Wedfourth);
//        lecturesOnWednesday.add(Wedfifth);
//        MyAdapter1 adapterWed = new MyAdapter1(getContext(),R.layout.timetable_row,lecturesOnWednesday);
//        listViewWed.setAdapter(adapterWed);
////
////                         /*Thursday_Lectures*/
//        ListView listViewThur = view.findViewById(R.id.timetable_listview_thur);
////
//        Monday  Thurfirst = new Monday("Applied Mathematics III","Abhiruchi Dakwale ","Theory","10:30 AM","11:30 AM","205");
//        Monday  Thursecond= new Monday("Data Structure","Jayant Sawarkar ","Theory","11:30 AM","12:30 PM","206");
//        Monday  Thurthird = new Monday("Digital Logic & Computer Organization and Architecture","ssk ","Theory","1:00 PM","2:00 PM","302");
//        Monday  Thurfourth = new Monday("Computer Graphics","prasant Itankar ","Theory","2:00 PM","3:00 PM","206");
//        Monday  Thurfifth = new Monday("OOPM","Shreya Patankar ","practical","3:00 AM","4:00 PM","101");
////
//        ArrayList<Monday> lecturesOnThursday = new ArrayList<>();
//        lecturesOnThursday.add(Thurfirst);
//        lecturesOnThursday.add(Thursecond);
//        lecturesOnThursday.add(Thurthird);
//        lecturesOnThursday.add(Thurfourth);
//        lecturesOnThursday.add(Thurfifth);
//        MyAdapter1 adapterThur = new MyAdapter1(getContext(),R.layout.timetable_row,lecturesOnThursday);
//        listViewThur.setAdapter(adapterThur);
////
////                        /*Friday_Lectures*/
//        ListView listViewFri = view.findViewById(R.id.timetable_listview_fri);
////
//        Monday  Frifirst = new Monday("Applied Mathematics III","Abhiruchi Dakwale ","Theory","10:30 AM","11:30 AM","205");
//        Monday  Frisecond= new Monday("Data Structure","Jayant Sawarkar ","Theory","11:30 AM","12:30 PM","206");
//        Monday  Frithird = new Monday("Digital Logic & Computer Organization and Architecture","ssk ","Theory","1:00 PM","2:00 PM","302");
//        Monday  Frifourth = new Monday("Computer Graphics","prasant Itankar ","Theory","2:00 PM","3:00 PM","206");
//        Monday  Frififth = new Monday("OOPM","Shreya Patankar ","practical","3:00 AM","4:00 PM","101");
//
//        ArrayList<Monday> lecturesOnFriday = new ArrayList<>();
//        lecturesOnFriday.add(Frifirst);
//        lecturesOnFriday.add(Frisecond);
//        lecturesOnFriday.add(Frithird);
//        lecturesOnFriday.add(Frifourth);
//        lecturesOnFriday.add(Frififth);
//        MyAdapter1 adapterFri = new MyAdapter1(getContext(),R.layout.timetable_row,lecturesOnFriday);
//        listViewFri.setAdapter(adapterFri);
        getContext();
        return view;
    }

}