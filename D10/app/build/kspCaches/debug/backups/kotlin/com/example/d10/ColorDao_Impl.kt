package com.example.d10

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ColorDao_Impl(
  __db: RoomDatabase,
) : ColorDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfColorItem: EntityInsertAdapter<ColorItem>

  private val __deleteAdapterOfColorItem: EntityDeleteOrUpdateAdapter<ColorItem>
  init {
    this.__db = __db
    this.__insertAdapterOfColorItem = object : EntityInsertAdapter<ColorItem>() {
      protected override fun createQuery(): String = "INSERT OR ABORT INTO `colors` (`id`,`name`,`hex`) VALUES (nullif(?, 0),?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ColorItem) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.hex)
      }
    }
    this.__deleteAdapterOfColorItem = object : EntityDeleteOrUpdateAdapter<ColorItem>() {
      protected override fun createQuery(): String = "DELETE FROM `colors` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: ColorItem) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
  }

  public override suspend fun insertColor(color: ColorItem): Unit = performSuspending(__db, false, true) { _connection ->
    __insertAdapterOfColorItem.insert(_connection, color)
  }

  public override suspend fun deleteColor(color: ColorItem): Unit = performSuspending(__db, false, true) { _connection ->
    __deleteAdapterOfColorItem.handle(_connection, color)
  }

  public override fun getAllColors(): Flow<List<ColorItem>> {
    val _sql: String = "SELECT * FROM colors ORDER BY id DESC"
    return createFlow(__db, false, arrayOf("colors")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _columnIndexOfHex: Int = getColumnIndexOrThrow(_stmt, "hex")
        val _result: MutableList<ColorItem> = mutableListOf()
        while (_stmt.step()) {
          val _item: ColorItem
          val _tmpId: Int
          _tmpId = _stmt.getLong(_columnIndexOfId).toInt()
          val _tmpName: String
          _tmpName = _stmt.getText(_columnIndexOfName)
          val _tmpHex: String
          _tmpHex = _stmt.getText(_columnIndexOfHex)
          _item = ColorItem(_tmpId,_tmpName,_tmpHex)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
