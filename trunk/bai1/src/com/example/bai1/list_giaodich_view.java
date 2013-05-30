package com.example.bai1;


import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * lớp tương ứng với 1 item trên Custom-ListView 
 */
public class list_giaodich_view extends LinearLayout {
    //các thuộc tính tương ứng với các View Widget 
    //trong tập tin list_item.xml
    
    public TextView     name;
    public TextView     money;
    public TextView     type;
    public list_giaodich_view(Context context) {
        super(context);
         
        //đọc tập tin list_item.xml để lấy các thành phần
        LayoutInflater linflater = (LayoutInflater) ((sogiaodichphu)context).getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        linflater.inflate(R.layout.list_view_giaodich, this);
         
        //lấy các thành  phần tương ứng
        
        this.name   = (TextView) findViewById(R.id.itemName);
        this.money   = (TextView) findViewById(R.id.itemMoney);
        this.type = (TextView) findViewById(R.id.itemType);
    }
     
    /**
     * phương thức đặt data vào trong phần tử VietnameseView
     * @param item
     */
    public void setListItem(list_giaodich item){
        
        this.name.setText(item.name);
        this.money.setText(item.money);
        this.type.setText(item.type);
    }
    }