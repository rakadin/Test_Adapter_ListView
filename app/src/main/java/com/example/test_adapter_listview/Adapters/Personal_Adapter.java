package com.example.test_adapter_listview.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test_adapter_listview.R;
import com.example.test_adapter_listview.Subjects.Person;

import java.util.ArrayList;
import java.util.List;

public class Personal_Adapter extends BaseAdapter {
    //Dữ liệu liên kết bởi Adapter là một mảng các sản phẩm
    final ArrayList<Person> listProduct;

    public Personal_Adapter(ArrayList<Person> listProduct) {
        this.listProduct = listProduct;
    }
    @Override
    public int getCount() {
        //Trả về tổng số phần tử, nó được gọi bởi ListView
        return listProduct.size();
    }

    @Override
    public Object getItem(int position) {
        //Trả về dữ liệu ở vị trí position của Adapter, tương ứng là phần tử
        //có chỉ số position trong listProduct
        return listProduct.get(position);
    }

    @Override
    public long getItemId(int position) {
        //Trả về một ID của phần
        return listProduct.get(position).ID;
    }
    // set list view
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //convertView là View của phần tử ListView, nếu convertView != null nghĩa là
        //View này được sử dụng lại, chỉ việc cập nhật nội dung mới
        //Nếu null cần tạo mới

        View viewProduct;
        if (convertView == null) {
            viewProduct = View.inflate(parent.getContext(), R.layout.list_items_view, null);
        } else viewProduct = convertView;

        //Bind sữ liệu phần tử vào View
        Person person = (Person) getItem(position);
        ((TextView) viewProduct.findViewById(R.id.txtstt)).setText( Integer.toString(person.ID));
        ((TextView) viewProduct.findViewById(R.id.txtname)).setText( person.name);
        ((TextView) viewProduct.findViewById(R.id.txtCMND)).setText( person.CMND);
        ((TextView) viewProduct.findViewById(R.id.txtdegre)).setText(person.Degre);
        ((TextView) viewProduct.findViewById(R.id.txtlike)).setText(person.Like);

        return viewProduct;
    }
}
