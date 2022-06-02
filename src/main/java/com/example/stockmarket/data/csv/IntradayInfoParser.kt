package com.example.stockmarket.data.csv

import com.example.stockmarket.data.mapper.toIntradayInfo
import com.example.stockmarket.data.remote.dto.IntradayInfoDto
import com.example.stockmarket.domain.model.IntradayInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntradayInfoParser @Inject constructor(): CsvParser<IntradayInfo> {


    override suspend fun parse(inputStream: InputStream): List<IntradayInfo> {
        val csvReader = CSVReader(InputStreamReader(inputStream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1) /** drop count column */
                .mapNotNull { line ->
                    val time = line.getOrNull(0) ?: return@mapNotNull null
                    val close = line.getOrNull(4) ?: return@mapNotNull null
                    val dto = IntradayInfoDto(time, close.toDouble())
                    dto.toIntradayInfo()
                } /** get close values of last 4 days ordered by hours */
                .filter {
                    it.date.dayOfMonth == LocalDate.now().minusDays(1).dayOfMonth ||
                            it.date.dayOfMonth == LocalDate.now().minusDays(2).dayOfMonth ||
                            it.date.dayOfMonth == LocalDate.now().minusDays(3).dayOfMonth ||
                            it.date.dayOfMonth == LocalDate.now().minusDays(4).dayOfMonth
                }
                .sortedBy {
                    it.date.hour
                }
                .also {
                    csvReader.close()
                }
        }
    }
}