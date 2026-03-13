package com.example.mobile_app.model;

import java.util.ArrayList;
import java.util.List;

public class RoomData {
    public static List<Room> roomList = new ArrayList<>();

    static {
        // Thêm dữ liệu mẫu để test giao diện
        roomList.add(new Room("1", "101", 1500000, false, "", ""));
        roomList.add(new Room("2", "102", 1800000, true, "Nguyễn Văn An", "0901234567"));
        roomList.add(new Room("3", "201", 2200000, false, "", ""));
        roomList.add(new Room("4", "202", 2500000, true, "Lê Thị Bình", "0988777666"));
        roomList.add(new Room("5", "301", 3000000, false, "", ""));
        roomList.add(new Room("6", "302", 3500000, true, "Trần Văn Cường", "0912345678"));
        roomList.add(new Room("7", "401", 4000000, false, "", ""));
        roomList.add(new Room("8", "402", 4500000, true, "Phạm Thị Dung", "0977666555"));
        roomList.add(new Room("9", "501", 5000000, false, "", ""));
        roomList.add(new Room("10", "502", 5500000, true, "Hoàng Văn Em", "0966555444"));
    }
}
