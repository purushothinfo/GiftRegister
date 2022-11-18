/*
 * **
 * Pi App Studio. All rights reserved.Copyright (c) 2022.
 *
 */

package com.piappstudio.pimodel.excel


import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


/**
 * Creates an Excel Worksheet
 * Excel Worksheet Utility Methods
 *
 * Created by: Ranit Raj Ganguly on 16/04/21.
 */
object ExcelUtils {
    const val TAG = "ExcelUtil"
    private var cell: org.apache.poi.ss.usermodel.Cell? = null
    private var sheet: org.apache.poi.ss.usermodel.Sheet? = null
    private var workbook: org.apache.poi.ss.usermodel.Workbook? = null
    private var headerCellStyle: org.apache.poi.ss.usermodel.CellStyle? = null

    /**
     * Import data from Excel Workbook
     */
    fun readFromExcelWorkbook(filePath: String?): Boolean {
        // TODO
        return false
    }

    /**
     * Export Data into Excel Workbook
     *
     * @param context - Pass the application context
     * @param fileName - Pass the desired fileName for the output excel Workbook
     * @param dataList - Contains the actual data to be displayed in excel
     */
    fun exportDataIntoWorkbook(
        context: Context, fileName: String,
        dataList: List<ContactResponse>
    ): Boolean {
        val isWorkbookWrittenIntoStorage: Boolean

        // Check if available and not read only
        if (!isExternalStorageAvailable || isExternalStorageReadOnly) {
            Log.e(TAG, "Storage not available or read only")
            return false
        }

        // Creating a New HSSF Workbook (.xls format)
        workbook = org.apache.poi.hssf.usermodel.HSSFWorkbook()
        setHeaderCellStyle()

        // Creating a New Sheet and Setting width for each column
        sheet = workbook.createSheet(Constants.EXCEL_SHEET_NAME)
        sheet.setColumnWidth(0, 15 * 400)
        sheet.setColumnWidth(1, 15 * 400)
        sheet.setColumnWidth(2, 15 * 400)
        sheet.setColumnWidth(3, 15 * 400)
        setHeaderRow()
        fillDataIntoExcel(dataList)
        isWorkbookWrittenIntoStorage = storeExcelInStorage(context, fileName)
        return isWorkbookWrittenIntoStorage
    }

    /**
     * Checks if Storage is READ-ONLY
     *
     * @return boolean
     */
    private val isExternalStorageReadOnly: Boolean
        private get() {
            val externalStorageState = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED_READ_ONLY == externalStorageState
        }

    /**
     * Checks if Storage is Available
     *
     * @return boolean
     */
    private val isExternalStorageAvailable: Boolean
        private get() {
            val externalStorageState = Environment.getExternalStorageState()
            return Environment.MEDIA_MOUNTED == externalStorageState
        }

    /**
     * Setup header cell style
     */
    private fun setHeaderCellStyle() {
        headerCellStyle = workbook.createCellStyle()
        headerCellStyle.setFillForegroundColor(org.apache.poi.hssf.util.HSSFColor.AQUA.index)
        headerCellStyle.setFillPattern(org.apache.poi.hssf.usermodel.HSSFCellStyle.SOLID_FOREGROUND)
        headerCellStyle.setAlignment(org.apache.poi.ss.usermodel.CellStyle.ALIGN_CENTER)
    }

    /**
     * Setup Header Row
     */
    private fun setHeaderRow() {
        val headerRow: org.apache.poi.ss.usermodel.Row = sheet.createRow(0)
        cell = headerRow.createCell(0)
        cell.setCellValue("First Name")
        cell.setCellStyle(headerCellStyle)
        cell = headerRow.createCell(1)
        cell.setCellValue("Last Name")
        cell.setCellStyle(headerCellStyle)
        cell = headerRow.createCell(2)
        cell.setCellValue("Phone Number")
        cell.setCellStyle(headerCellStyle)
        cell = headerRow.createCell(3)
        cell.setCellValue("Mail ID")
        cell.setCellStyle(headerCellStyle)
    }

    /**
     * Fills Data into Excel Sheet
     *
     * NOTE: Set row index as i+1 since 0th index belongs to header row
     *
     * @param dataList - List containing data to be filled into excel
     */
    private fun fillDataIntoExcel(dataList: List<ContactResponse>) {
        for (i in dataList.indices) {
            // Create a New Row for every new entry in list
            val rowData: org.apache.poi.ss.usermodel.Row = sheet.createRow(i + 1)

            // Create Cells for each row
            cell = rowData.createCell(0)
            cell.setCellValue(dataList[i].firstName)
            cell = rowData.createCell(1)
            cell.setCellValue(dataList[i].lastName)
            cell = rowData.createCell(2)
            cell.setCellValue(dataList[i].phoneNumber)
            cell = rowData.createCell(3)
            cell.setCellValue(dataList[i].mailId)
        }
    }

    /**
     * Store Excel Workbook in external storage
     *
     * @param context - application context
     * @param fileName - name of workbook which will be stored in device
     *
     * @return boolean - returns state whether workbook is written into storage or not
     */
    private fun storeExcelInStorage(context: Context, fileName: String): Boolean {
        var isSuccess: Boolean
        val file = File(context.getExternalFilesDir(null), fileName)
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = FileOutputStream(file)
            workbook.write(fileOutputStream)
            Log.e(TAG, "Writing file$file")
            isSuccess = true
        } catch (e: IOException) {
            Log.e(TAG, "Error writing Exception: ", e)
            isSuccess = false
        } catch (e: Exception) {
            Log.e(TAG, "Failed to save file due to Exception: ", e)
            isSuccess = false
        } finally {
            try {
                fileOutputStream?.close()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
        return isSuccess
    }
}