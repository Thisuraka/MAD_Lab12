package com.example.lab12_model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class MessageAdapter extends ArrayAdapter<Message> {

    private Context context;
    private int resource;
    List<Message> messages;

    MessageAdapter(Context context, int resource, List<Message> messages) {
        super(context, resource, messages);
        this.context = context;
        this.resource = resource;
        this.messages = messages;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(resource, parent, false);

        TextView sub = row.findViewById(R.id.sub);
        TextView msg = row.findViewById(R.id.msg);

        Message message = messages.get(position);
        sub.setText(message.getSubject());
        msg.setText(message.getMessage());

        return row;
    }
}