import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.*;
import com.google.api.services.drive.Drive;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class Main extends JFrame implements ActionListener {


    private static boolean iGoogleFileFound = false;

    public static void main(String args[]){

        checkGoogleFile();

        if(iGoogleFileFound){

        }else{
            System.out.println("###:Kein File gefunden oder erstellt");
        }


    }

    private static void checkGoogleFile(){
        try{
            // Build a new authorized API client service.
            Drive service = Quickstart.getDriveService();
            //service.files().get("");
            FileList result = service.files().list()
                    .setPageSize(100)
                    .setFields("nextPageToken, files(id, name)")
                    .execute();

            for(com.google.api.services.drive.model.File file : result.getFiles()){
                if(file.getName().equals("testTimeBookerChart")){
                    iGoogleFileFound = true;
                }

                //MessageBox you want to Create the File?
            }

        }catch(IOException e){
            System.out.println("###:Fehler beim google connect");
        }
    }

    public void actionPerformed(ActionEvent event){

    }
}
