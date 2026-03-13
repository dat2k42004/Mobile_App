package com.example.rental_room.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_app.R;
import com.example.mobile_app.model.Room;
import com.example.mobile_app.model.RoomData;

public class EditRoomActivity extends AppCompatActivity {
    EditText edtName, edtPrice, edtTenant, edtPhone;
    CheckBox chkRented;
    Button btnUpdate;
    int position;
    Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

        // Ánh xạ view
        edtName = findViewById(R.id.edit_room_name);
        edtPrice = findViewById(R.id.edit_room_price);
        edtTenant = findViewById(R.id.edit_tenant_name);
        edtPhone = findViewById(R.id.edit_tenant_phone);
        chkRented = findViewById(R.id.edit_check_rented);
        btnUpdate = findViewById(R.id.btn_update_confirm);

        // Nhận dữ liệu từ Intent
        position = getIntent().getIntExtra("POSITION", -1);
        room = (Room) getIntent().getSerializableExtra("ROOM_OBJ");

        if (position == -1 || room == null) {
            Toast.makeText(this, "Không tìm thấy dữ liệu phòng!", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Đổ dữ liệu lên view
        edtName.setText(room.getName());
        edtPrice.setText(String.valueOf(room.getPrice()));
        edtTenant.setText(room.getTenantName());
        edtPhone.setText(room.getPhone());
        chkRented.setChecked(room.isRented());

        // Xử lý nút cập nhật
        btnUpdate.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String priceStr = edtPrice.getText().toString().trim();
            String tenant = edtTenant.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            boolean isRented = chkRented.isChecked();

            if (name.isEmpty()) {
                edtName.setError("Tên không được để trống");
                return;
            }

            // Kiểm tra trùng tên (ngoại trừ tên hiện tại)
            for (int i = 0; i < RoomData.roomList.size(); i++) {
                if (i != position && RoomData.roomList.get(i).getName().equalsIgnoreCase(name)) {
                    edtName.setError("Tên phòng này đã tồn tại ở phòng khác!");
                    return;
                }
            }

            if (priceStr.isEmpty()) {
                edtPrice.setError("Giá thuê không được để trống");
                return;
            }

            try {
                double price = Double.parseDouble(priceStr);
                if (price < 0) {
                    edtPrice.setError("Giá thuê không được âm");
                    return;
                }

                if (isRented && tenant.isEmpty()) {
                    Toast.makeText(this, "Vui lòng nhập tên người thuê!", Toast.LENGTH_SHORT).show();
                    return;
                }

                room.setName(name);
                room.setPrice(price);
                room.setTenantName(tenant);
                room.setPhone(phone);
                room.setRented(isRented);

                RoomData.roomList.set(position, room);
                
                Toast.makeText(this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                finish();
            } catch (NumberFormatException e) {
                edtPrice.setError("Giá thuê phải là số hợp lệ");
            }
        });
    }
}
