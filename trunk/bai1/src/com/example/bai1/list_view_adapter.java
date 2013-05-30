package com.example.bai1;
import java.util.List;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
public class list_view_adapter extends ArrayAdapter<list_giaodich> {
	private List<list_giaodich>  _listItems;
    private Context         _context;
	 public list_view_adapter(Context context, int textViewResourceId,
			List<list_giaodich> objects) {
		 super(context, textViewResourceId, objects);
	        this._context   = context;
	        this._listItems = objects;
	    }
	  @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        list_giaodich_view view = new list_giaodich_view (this._context);
	        view.setListItem(this._listItems.get(position));
	        return view;
	  
}
}	


