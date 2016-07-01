//package com.example.onno.testapp;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.View;
//
///**
// * Created by onno on 23-6-2016.
// */
//public class ActivityButton {
//    String nameButton;
//    String nameActivity;
//    Context context;
//    public ActivityButton(String nameButton,String nameActivity)
//    {
//        this.nameButton = nameButton;
//        this.nameActivity = nameActivity;
//    }
//
//    context = getApplicationContext();
//    nameButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//
//        public void onClick(View view) {
//            Intent intent = new Intent(context,nameActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivity(intent);
//
//}
