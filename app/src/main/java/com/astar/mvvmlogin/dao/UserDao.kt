package com.astar.mvvmlogin.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.astar.mvvmlogin.model.Account

@Dao
interface UserDao {

    @Query("select * from account")
    fun select(): Account?

    @Insert(onConflict = REPLACE)
    fun insert(account: Account)

    @Query("delete from account")
    fun delete()
}