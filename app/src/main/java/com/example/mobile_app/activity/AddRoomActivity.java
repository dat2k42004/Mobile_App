package com.example.mobile_app.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobile_app.R;
import com.example.mobile_app.model.Room;
import com.example.mobile_app.model.RoomData;

import java.util.UUID;

public class AddRoomActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        EditText edtName = findViewById(R.id.edtName);
        EditText edtPrice = findViewById(R.id.edtPrice);
        EditText edtTenant = findViewById(R.id.edtTenant);
        EditText edtPhone = findViewById(R.id.edtPhone);
        CheckBox chkRented = findViewById(R.id.chkRented);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String priceStr = edtPrice.getText().toString().trim();
            String tenant = edtTenant.getText().toString().trim();
            String phone = edtPhone.getText().toString().trim();
            boolean isRented = chkRented.isChecked();

            // 1. Validate Tên phòng
            if (name.isEmpty()) {
                edtName.setError("Tên phòng không được để trống");
                edtName.requestFocus();
                return;
            }

            // 2. Kiểm tra trùng tên phòng
            for (Room r : RoomData.roomList) {
                if (r.getName().equalsIgnoreCase(name)) {
                    edtName.setError("Phòng này đã tồn tại trong hệ thống");
                    edtName.requestFocus();
                    return;
                }
            }

            // 3. Validate Giá thuê
            if (priceStr.isEmpty()) {
                edtPrice.setError("Vui lòng nhập giá thuê");
                edtPrice.requestFocus();
                return;
            }

            double price;
            try {
                price = Double.parseDouble(priceStr);
                if (price <= 0) {
                    edtPrice.setError("Giá thuê phải lớn hơn 0");
                    edtPrice.requestFocus();
                    return;
                }
            } catch (NumberFormatException e) {
                edtPrice.setError("Giá thuê phải là số hợp lệ");
                edtPrice.requestFocus();
                return;
            }

            // 4. Validate thông tin khách thuê nếu đã đánh dấu 'Đã thuê'
            if (isRented) {
                if (tenant.isEmpty()) {
                    edtTenant.setError("Cần nhập tên khách thuê");
                    edtTenant.requestFocus();
                    return;
                }
                
                // Kiểm tra tên người thuê không chứa số
                if (!tenant.matches("^[\\p{L} ]+$")) {
                    edtTenant.setError("Tên người thuê chỉ được chứa chữ cái");
                    edtTenant.requestFocus();
                    return;
                }

                if (phone.isEmpty()) {
                    edtPhone.setError("Cần nhập số điện thoại khách");
                    edtPhone.requestFocus();
                    return;
                }
                
                // Kiểm tra số điện thoại chỉ chứa số (đã có regex d{10,11})
                if (!phone.matches("^\\d{10,11}$")) {
                    edtPhone.setError("Số điện thoại không hợp lệ (phải là số, từ 10-11 chữ số)");
                    edtPhone.requestFocus();
                    return;
                }
            }

            // Nếu mọi thứ hợp lệ -> Lưu
            Room newRoom = new Room(
                    UUID.randomUUID().toString(),
                    name,
                    price,
                    isRented,
                    tenant,
                    phone
            );

            RoomData.roomList.add(newRoom);
            Toast.makeText(this, "Thêm phòng mới thành công!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
