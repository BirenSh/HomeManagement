package com.example.homemanagement.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.android.gms.common.util.Clock
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object CalenderUtil {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDate(): LocalDate {
        return LocalDate.now()
    }
}