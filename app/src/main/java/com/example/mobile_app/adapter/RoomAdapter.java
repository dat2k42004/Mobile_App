package com.example.mobile_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_app.R;
import com.example.mobile_app.activity.DeleteRoomActivity;
import com.example.mobile_app.activity.RoomDetailActivity;
import com.example.mobile_app.activity.EditRoomActivity;
import com.example.mobile_app.model.Room;
import com.example.mobile_app.model.RoomData;


public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    private Context context;

    public RoomAdapter(Context context) { this.context = context; }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = RoomData.roomList.get(position);
        holder.tvName.setText("Phòng: " + room.getName());
        holder.tvPrice.setText("Giá: " + room.getPrice() + " VNĐ");

        if (room.isRented()) {
            holder.tvStatus.setText("Tình trạng: Đã thuê");
            holder.tvStatus.setTextColor(Color.RED);
        } else {
            holder.tvStatus.setText("Tình trạng: Còn trống");
            holder.tvStatus.setTextColor(Color.GREEN);
        }


        // handle click Edit button
        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditRoomActivity.class);
            intent.putExtra("POSITION", position);
            intent.putExtra("ROOM_OBJ", room);
            context.startActivity(intent);
        });


        // Nút Xóa
        holder.btnDelete.setOnClickListener(v -> {
            Intent intent = new Intent(context, DeleteRoomActivity.class);
            intent.putExtra("POSITION", position);
            intent.putExtra("ROOM_OBJ", room);
            context.startActivity(intent);
        });

        // view
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RoomDetailActivity.class);
            intent.putExtra("ROOM_OBJ", room);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() { return RoomData.roomList.size(); }

    class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvPrice, tvStatus;
        Button btnEdit, btnDelete;
        public RoomViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            btnEdit = itemView.findViewById(R.id.btnEditItem);
            btnDelete = itemView.findViewById(R.id.btnDeleteItem);
        }
    }
}
