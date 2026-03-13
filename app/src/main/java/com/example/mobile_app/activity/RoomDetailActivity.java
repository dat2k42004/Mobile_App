package com.example.mobile_app.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_app.R;
import com.example.mobile_app.model.Room;

public class RoomDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvPrice = findViewById(R.id.tvDetailPrice);
        TextView tvStatus = findViewById(R.id.tvDetailStatus);
        TextView tvTenant = findViewById(R.id.tvDetailTenant);
        TextView tvPhone = findViewById(R.id.tvDetailPhone);
        Button btnBack = findViewById(R.id.btnBackDetail);

        Room room = (Room) getIntent().getSerializableExtra("ROOM_OBJ");

        if (room != null) {
            tvName.setText("Phòng: " + room.getName());
            tvPrice.setText("Giá thuê: " + String.format("%,.0f", room.getPrice()) + " VNĐ");

            if (room.isRented()) {
                tvStatus.setText("Trạng thái: Đã thuê");
                tvStatus.setTextColor(Color.RED);
                tvTenant.setText("Người thuê: " + room.getTenantName());
                tvPhone.setText("Số điện thoại: " + room.getPhone());
            } else {
                tvStatus.setText("Trạng thái: Còn trống");
                tvStatus.setTextColor(Color.GREEN);
                tvTenant.setText("Người thuê: (Trống)");
                tvPhone.setText("Số điện thoại: (Trống)");
            }
        } else {
            Toast.makeText(this, "Không có dữ liệu!", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnBack.setOnClickListener(v -> finish());
    }
}

