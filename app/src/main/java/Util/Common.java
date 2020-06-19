package Util;

import android.content.Context;
import android.util.Log;

import com.example.bodegacarga.R;

import java.io.File;

public class Common {

    public static String getAppPath(Context context){

        File dir = new File(android.os.Environment.getExternalStorageDirectory()
                + File.separator
                + context.getResources().getString(R.string.app_name)
                + File.separator
        );

        if(dir.exists()) {
            try{
                dir.delete();
                dir.mkdirs();
            }catch (Exception ex)
            {
                Log.e("Create DIR ERROR: ", ex.getMessage());
            }
        }else{
            try{
                dir.mkdirs();
            }catch(Exception ex){
                Log.e("Create DIR ERROR: ", ex.getMessage());
            }
        }
        return dir.getPath() + File.separator;

    }

}
