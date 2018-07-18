package app.baochequan.cn.androidstudy;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import app.baochequan.cn.androidstudy.adapter.CardViewAdapter;
import app.baochequan.cn.androidstudy.model.ItemCard;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private CardViewAdapter cardViewAdapter;
    private List<ItemCard> itemCards=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycleView);
        //recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        itemCards.add(new ItemCard(0,"java基础0","java0.pdf"));
        itemCards.add(new ItemCard(1,"java基础1","java1.pdf"));
        itemCards.add(new ItemCard(2,"java基础2","java2.pdf"));
        itemCards.add(new ItemCard(3,"java基础3","java3.pdf"));
        itemCards.add(new ItemCard(4,"自定义view","java4.pdf"));
        itemCards.add(new ItemCard(5,"安卓源码","java5.pdf"));
        cardViewAdapter=new CardViewAdapter(this,itemCards);
        recyclerView.setAdapter(cardViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       super.onCreateOptionsMenu(menu);
        menu.add(Menu.NONE,  Menu.FIRST+1 , 0, "清除缓存").setIcon(R.drawable.count);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) //得到被点击的item的itemId
        {
            case  Menu.FIRST+1 :  //对应的ID就是在add方法中所设定的Id
                AbFileUtil.deleteDirectory(Environment.getExternalStorageDirectory().getAbsolutePath()+"/gaohangbo",false);
                break;
            case  Menu.FIRST+2 :
                break;
        }
        return true;
    }
}