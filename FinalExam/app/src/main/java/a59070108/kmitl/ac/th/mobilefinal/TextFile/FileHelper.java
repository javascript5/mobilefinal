package a59070108.kmitl.ac.th.mobilefinal.TextFile;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import a59070108.kmitl.ac.th.mobilefinal.User;

public class FileHelper {
    final static String fileName = "data.txt";
    final static String path = Environment.getExternalStorageDirectory().getPath() + "/readwrite/" ;
    final static String TAG = FileHelper.class.getName();
    private static Context context;

    public FileHelper(Context context){
        this.context = context;
    }
    public static String ReadFile(String userId){
        String line = null;
        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path +"/"+userId+"/"+ userId+".txt"));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
            }

//            Print Data ----------------------------
//            for(String[] strArray : arrayList) {
//                for(String str : strArray) {
//                    Log.i("Text", str + "");
//                }
//                Log.i("Text", "____");
//            }

            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }
        catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return line;
    }

    public static void generateNote(String userId) {
        Log.i("RegisterFragment", path);
        try {
            File root = new File(path+userId);
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, userId+".txt");
            FileWriter writer = new FileWriter(gpxfile);
            writer.append("");
            writer.flush();
            writer.close();
            Log.d("RegisterFragment", "Created File");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean saveToFile(String fileName, String data){
        try {
            new File(path).mkdir();
            File file = new File(path+"/"+fileName+"/"+ fileName+".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,false);
            fileOutputStream.write(data.getBytes());

            return true;
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;


    }
}
