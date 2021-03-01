package com.util.logutil;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveFile {

    public  void saveInFilesDir(Context content, String fileName, String fileContent) {

        try {
            FileOutputStream fos = content.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(fileContent.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
