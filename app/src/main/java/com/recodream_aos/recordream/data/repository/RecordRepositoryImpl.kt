package com.recodream_aos.recordream.data.repository // ktlint-disable package-name

import android.icu.util.Calendar
import com.recodream_aos.recordream.domain.repository.RecordRepository

class RecordRepositoryImpl : RecordRepository {
    override suspend fun getCalendar(): Calendar {
        val cal = Calendar.getInstance()
    }
}
