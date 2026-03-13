package com.example.mobile_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_app.adapter.RoomAdapter;
import com.example.mobile_app.activity.AddRoomActivity;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvRooms;
    RoomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Gọi cái file XML ở trên đây

        rvRooms = findViewById(R.id.rvRooms);
        // Thiết lập RecyclerView theo dạng danh sách dọc
        rvRooms.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo Adapter và gán vào RecyclerView
        adapter = new RoomAdapter(this);
        rvRooms.setAdapter(adapter);

        // Xử lý nút thêm phòng
        findViewById(R.id.btnAddRoom).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddRoomActivity.class);
            startActivity(intent);
        });

    }

    // Quan trọng: Khi từ màn hình Thêm/Sửa quay về, phải báo Adapter load lại dữ liệu
    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}