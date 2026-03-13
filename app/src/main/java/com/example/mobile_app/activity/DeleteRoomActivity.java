package com.example.mobile_app.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_app.R;
import com.example.mobile_app.model.Room;
import com.example.mobile_app.model.RoomData;

public class DeleteRoomActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_room);

        TextView tvInfo = findViewById(R.id.tvDeleteInfo);
        Button btnConfirm = findViewById(R.id.btnConfirmDelete);
        Button btnCancel = findViewById(R.id.btnCancelDelete);

        int position = getIntent().getIntExtra("POSITION", -1);
        Room room = (Room) getIntent().getSerializableExtra("ROOM_OBJ");

        if (position != -1 && room != null) {
            tvInfo.setText("Bạn có chắc chắn muốn xóa phòng:\n" + room.getName() + " không?");
        }

        btnConfirm.setOnClickListener(v -> {
            if (position != -1) {
                RoomData.roomList.remove(position);
                Toast.makeText(this, "Đã xóa phòng thành công!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
