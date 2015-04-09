/*
 * Copyright 2014 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.pablogil.watchpresenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.pablogil.myapplication.backend.messaging.Messaging;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.pablogil.watchpresenter.messaging.GcmSendMessageAsyncTask;
import com.google.pablogil.watchpresenter.messaging.MessagingService;

/**
 * Created by pablogil on 1/4/15.
 */
public class SendMessageReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        final String message = intent.getStringExtra(Constants.EXTRA_MESSAGE);
        Log.v(Constants.LOG_TAG, "SendMessageReceiver. Message: " + message);
        Messaging messagingService = MessagingService.get(context);
        if(messagingService != null){
            Log.v(Constants.LOG_TAG, "Initiating send message task");
            new GcmSendMessageAsyncTask(messagingService).
                    execute(new String[]{message});
        }
        else{
            Log.i(Constants.LOG_TAG, "No messagingService available at the moment");
        }
    }
}
