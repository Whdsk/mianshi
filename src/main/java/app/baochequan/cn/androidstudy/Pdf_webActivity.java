package app.baochequan.cn.androidstudy;

import android.Manifest;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnDrawListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import app.baochequan.cn.androidstudy.adapter.CardViewAdapter;
import app.baochequan.cn.androidstudy.model.ItemCard;

/**
 * author gaohangbo
 * date: 2018/7/16 0016.
 */
public class Pdf_webActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private PDFView pdfView;
    private static boolean OVERM = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    private RecyclerView recyclerView;
    private CardViewAdapter cardViewAdapter;
    private List<ItemCard> itemCards=new ArrayList<>();
     private String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_web);
        pdfView=findViewById(R.id.pdfView);
        url=getIntent().getStringExtra("url");
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gaohangbo");
        if(file.exists()){
            file.delete();
        }
        filePath(file);
    }
    private boolean bAssetsFile(String pt) {
        AssetManager am = getAssets();
        try {
            String[] names = am.list("");
            for (int i = 0; i < names.length; i++) {
                //Log.d("FILES",names[i]);
                if (names[i].equals(pt.trim())) {
                    System.out.println(pt + "文件存在！！！！");
                    Log.i("sssssssss", "文件存在！！！！");
                    return true;
                } else {
                    //System.out.println(pt+"不存在啦！！！！");
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("不存在！");
        Log.i("sssssssss", "不存在！！");
        return false;
    }

    //遍历SDcard的所有文件.
    public void filePath(File file) {
        if (file != null && file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file2 : files) {
                String logfile=Environment.getExternalStorageDirectory().getAbsolutePath()+"/gaohangbo/"+url;
                 //String aa=  file2.getAbsolutePath();
                if(file2.getAbsolutePath().equals(logfile)){
                    pdfView.fromFile(file2).swipeHorizontal(false).enableDoubletap(true).defaultPage(0).onDraw(new OnDrawListener() {
                        @Override
                        public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {

                        }
                    }).onPageChange(new OnPageChangeListener() {
                        @Override
                        public void onPageChanged(int page, int pageCount) {
                            //Toast.makeText(Pdf_webActivity.this, "加油了，离成功又进一步了", Toast.LENGTH_LONG).show();
                        }
                    }).load();
                }else {
                    Toast.makeText(Pdf_webActivity.this,"加载文件失败",Toast.LENGTH_LONG).show();
                }
                if (file2.listFiles() == null) {
                    String str = "文件名称:" + file2.getName() + " 路径:" + file2.getPath();
                    System.out.println(str);
                } else
                    filePath(file2);
            }
        } else {
            try {
                File file1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/gaohangbo");
                if (!file1.exists()) {

                    requestPermission(file1);

                }
                Copy_rawUtils.copyFiles(this, "study", Environment.getExternalStorageDirectory().getAbsolutePath() + "/gaohangbo");
                // Utils.doCopy(this, "study", Environment.getExternalStorageDirectory().getAbsolutePath() + "/gaohangbo");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("文件不存在......");
            Log.i(TAG,"文件不存在......");
        }

    }

    private void requestPermission(File file) throws IOException {
        if (OVERM) {
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    .subscribe(permission -> { // will emit 2 Permission objects
                        if (permission.granted) {
                            file.createNewFile();
                            // `permission.name` is granted !
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            Toast.makeText(this,"权限被拒绝了", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this,"请在设置页面打开读取sd卡权限", Toast.LENGTH_LONG).show();
                            // Denied permission with ask never again
                            // Need to go to the settings
                        }
                    });
        }
        else {
            file.createNewFile();
        }
    }

}
