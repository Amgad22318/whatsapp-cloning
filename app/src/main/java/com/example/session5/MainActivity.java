package com.example.session5;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.session5.Adapters.pageAdapter;
import com.example.session5.Fragments.Camera_fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static ViewPager viewPager;
    private Uri uri = null;
    Toolbar toolbar;
    TabLayout tabLayout;
    AppBarLayout appBarLayout;
    StorageReference storageReference = FirebaseStorage
            .getInstance().getReference();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        appBarLayout = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        toolbar.canScrollVertically(Window.FEATURE_ACTION_BAR_OVERLAY);


        tabLayout = findViewById(R.id.tap_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                LinearLayout layout1 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(0));
                LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) layout1.getLayoutParams();
                layoutParams1.width = 5;
                layout1.setPadding(0, 0, 0, 0);
                layout1.setLayoutParams(layoutParams1);

                LinearLayout layout2 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(1));
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) layout2.getLayoutParams();
                layoutParams2.width = 300;
                layout2.setLayoutParams(layoutParams2);

                LinearLayout layout3 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(2));
                LinearLayout.LayoutParams layoutParams5 = (LinearLayout.LayoutParams) layout3.getLayoutParams();
                layoutParams5.width = 300;
                layout3.setLayoutParams(layoutParams5);

                LinearLayout layout4 = ((LinearLayout) ((LinearLayout) tabLayout.getChildAt(0)).getChildAt(3));
                LinearLayout.LayoutParams layoutParams4 = (LinearLayout.LayoutParams) layout4.getLayoutParams();
                layoutParams4.width = 300;
                layout4.setLayoutParams(layoutParams4);


            }
        }, 400);

        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.ic_camera_alt_white_40dp));
        tabLayout.getTabAt(0).getIcon().setTint(getResources().getColor(R.color.camera_tab));


        pageAdapter pageAdapter = new pageAdapter(getSupportFragmentManager(), (byte) tabLayout.getTabCount());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(pageAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(1);


        final FloatingActionButton floatingActionButton_Main = findViewById(R.id.floating_actionbutton_Main);
        final FloatingActionButton floatingActionButton1_addTextStatus = findViewById(R.id.floating_actionbutton_AddTextStatus);
        floatingActionButton_Main.setOnClickListener(this);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {

                    appBarLayout.setExpanded(false, true);
                    floatingActionButton_Main.setVisibility(View.GONE);
                    viewPager.setCurrentItem(tab.getPosition(), true);


                } else if (tab.getText().equals("Chats")) {

                    appBarLayout.setExpanded(true, true);
                    floatingActionButton_Main.setVisibility(View.VISIBLE);
                    floatingActionButton_Main.setImageResource(R.drawable.ic_new_chat);
                    viewPager.setCurrentItem(tab.getPosition(), true);
                    floatingActionButton1_addTextStatus.hide();
                } else if (tab.getText().equals("Status")) {
                    //appBarLayout.setExpanded(true,true);
                    floatingActionButton_Main.setVisibility(View.VISIBLE);
                    floatingActionButton_Main.setImageResource(R.drawable.ic_take_photo);
                    viewPager.setCurrentItem(tab.getPosition(), true);
                    floatingActionButton1_addTextStatus.show();

                } else if (tab.getText().equals("Calls")) {
                    //appBarLayout.setExpanded(true,true);
                    floatingActionButton_Main.setVisibility(View.VISIBLE);
                    floatingActionButton_Main.setImageResource(R.drawable.ic_call_white);
                    viewPager.setCurrentItem(tab.getPosition(), true);
                    floatingActionButton1_addTextStatus.hide();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_out_menuItem:
                firebaseAuth.signOut();
                Intent intent = new Intent(this, Sign.class);
                startActivity(intent);
                finish();
                break;
            case R.id.update_prof_pic:
                selectImage();
                break;

        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.floating_actionbutton_Main && viewPager.getCurrentItem() == 2) {
            Camera_fragment.cameraView.stop();
            Intent intent = new Intent(this, Camera_FAB.class);
            startActivity(intent);


        } else if (view.getId() == R.id.floating_actionbutton_Main && viewPager.getCurrentItem() == 1) {

            Intent intent = new Intent(this, Chat_FAB.class);
            startActivity(intent);
        }
    }

    private void selectImage() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK);
        pickPhoto.setType("image/*");
        startActivityForResult(pickPhoto, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == RESULT_OK && data != null) {
            uri = data.getData();
            uploadUserImage();

        }
    }


    private void uploadUserImage() {
        if (uri == null) {
            return;
        }

        storageReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("profile_photo").putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    storageReference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("profile_photo").getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                String url = task.getResult
                                        ().toString();

                                reference.child("users").child(firebaseAuth.getCurrentUser().getUid()).child("profile_photo").setValue(url);
                            }
                        }
                    });


                }

            }
        });


    }
}

