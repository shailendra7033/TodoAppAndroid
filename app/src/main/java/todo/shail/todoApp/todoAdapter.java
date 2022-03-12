package todo.shail.todoApp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.TodoViewHolder> {
    private ArrayList<String> data;
    private Context context;

    public todoAdapter(ArrayList<String> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_layout_item,parent,false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        String task=data.get(position);
        holder.txtView.setText(task);
        // click listener
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                

                holder.txtView.setText("Deleted");
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // class view holder
    public class TodoViewHolder extends RecyclerView.ViewHolder{

    public TextView txtView;
    public Button btnDelete;
        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            btnDelete=itemView.findViewById(R.id.btn_delete);
            txtView=itemView.findViewById(R.id.txt_list);
        }
    }

}


