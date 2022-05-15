package com.example.test1

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.test1.data.Employee


class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
         const val DATABASE_VERSION = 2
         const val id_COlOMN = "id"
         const val DATABASE_NAME = "Employee_Table"
         const val COLOMN_EMPId = "Empid"
         const val COLOMN_EMAIL = "email"
         const val COLOMN_NAME = "name"
         const val COLOMN_PASSWord = "Password"
         const val Employee_Table = "employee_table"

    }


    private  val createUserTable = ("CREATE TABLE " + Employee_Table +"(" + id_COlOMN + " INTEGER PRIMARY KEY ,"
            + COLOMN_EMPId + " TEXT ," + COLOMN_EMAIL + " TEXT ,"
            + COLOMN_NAME + " TEXT ," + COLOMN_PASSWord + " TEXT " +")")

    private val dropUserTable = "DROP TABLE IF EXISTS $Employee_Table"

    override fun onCreate(db: SQLiteDatabase?) {
      db?.execSQL(createUserTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL(dropUserTable)
    }


    @SuppressLint("Range")
    fun getEmployeeData(): ArrayList<Employee>  {
        val tableData = arrayOf(id_COlOMN, COLOMN_EMPId, COLOMN_EMAIL, COLOMN_NAME, COLOMN_PASSWord)
        val empList = ArrayList<Employee>()
        val sortOrder = "$COLOMN_EMAIL ASC"
        val db = this.readableDatabase
        val cursor = db.query(Employee_Table, tableData, null, null, null, null, sortOrder)

        if (cursor.moveToFirst()) {
            do {
                val emp = Employee(
                    id = cursor.getString(cursor.getColumnIndex(id_COlOMN)).toLong(),
                    employeeId = cursor.getString(cursor.getColumnIndex(COLOMN_EMPId)),
                    email = cursor.getString(cursor.getColumnIndex(COLOMN_EMAIL)),
                    name = cursor.getString(cursor.getColumnIndex(COLOMN_NAME)),
                    password = cursor.getString(cursor.getColumnIndex(COLOMN_PASSWord)))
                empList.add(emp)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return empList
    }

    fun insertUser(emp: Employee){
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLOMN_EMPId,emp.employeeId)
        contentValues.put(COLOMN_EMAIL,emp.email)
        contentValues.put(COLOMN_NAME,emp.name)
        contentValues.put(COLOMN_PASSWord,emp.password)
        db.insert(Employee_Table,null,contentValues)
        db.close()

    }

    fun checkEmpEmail(email: String): Boolean {
        val db = this.readableDatabase
        val column = arrayOf(id_COlOMN)
        val selection = "$COLOMN_EMAIL = ?"
        val selectionArgs = arrayOf(email)
        val cursor = db.query(Employee_Table, column, selection, selectionArgs, null, null, null)
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) {
            return true
        }
        return false

    }
//    fun checkEmpid(empid:String): Boolean {
//        val db = this.readableDatabase
//        val column = arrayOf(id_COlOMN)
//        val selection = "$COLOMN_EMPId = ?"
//        val selectionArgs = arrayOf(empid)
//        val cursor = db.query(Employee_Table, column, selection, selectionArgs, null, null, null)
//        val cursorCount = cursor.count
//        cursor.close()
//        db.close()
//        if (cursorCount > 0) {
//            return true
//        }
//        return false
//
//    }


    fun checkEmp(email:String,password:String):Boolean{
        val db = this.readableDatabase
        val columns = arrayOf(id_COlOMN)
        val select = "$COLOMN_EMAIL = ? AND $COLOMN_PASSWord = ?"
        val selectArgs= arrayOf(email,password)
        val cursor = db.query(Employee_Table, columns, select, selectArgs, null, null, null)
        val cursorCount = cursor.count
        cursor.close()
        db.close()
        if (cursorCount > 0) {
            return true
        }
        return false

    }



}


