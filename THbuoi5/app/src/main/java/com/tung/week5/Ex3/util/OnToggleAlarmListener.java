package com.thanh.week5.Ex3.util;

import android.view.View;

import com.thanh.week5.Ex3.model.Alarm;

public interface OnToggleAlarmListener {
    void onToggle(Alarm alarm);
    void onDelete(Alarm alarm);
    void onItemClick(Alarm alarm, View view);
}
