package com.example.online;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class contactadapter1
{
    List list=new ArrayList();
    public contactadapter1(Context context,int resource)
    {
        super(context,resource);
    }


    public void add(contact1 object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position );
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row=convertView;
        ContactHolder contactHolder;
        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.row_layout1,parent,false);
            contactHolder=new ContactHolder();
            contactHolder.tx_name=(TextView) row.findViewById(R.id.tx_name);
            contactHolder.tx_email=(TextView) row.findViewById(R.id.tx_email);
            contactHolder.tx_mobile=(TextView) row.findViewById(R.id.tx_mobile);
            row.setTag(contactHolder);
        }
        else
        {
            contactHolder=(ContactHolder) row.getTag();
        }

        contact1 contact1=(contact1) this.getItem(position);
        contactHolder.tx_name.setText(contact1.getName());
        contactHolder.tx_email.setText(contact1.getEmail());
        contactHolder.tx_mobile.setText(contact1.getMobile());
        return row;
    }

    static class ContactHolder
    {
        TextView tx_name,tx_email,tx_mobile;

    }

}
