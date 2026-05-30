package com.example.data

import android.content.Context
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type: String, // "Workshop", "Class", "Product"
    val itemName: String,
    val childName: String,
    val childAge: Int,
    val parentName: String,
    val parentPhone: String,
    val notes: String,
    val isOrder: Boolean,
    val status: String = "Pending Inquiry",
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "wishlist")
data class WishlistEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val itemName: String,
    val itemType: String,
    val price: String,
    val timestamp: Long = System.currentTimeMillis()
)

@Dao
interface PlayDao {
    @Query("SELECT * FROM bookings ORDER BY timestamp DESC")
    fun getAllBookings(): Flow<List<BookingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: BookingEntity)

    @Query("DELETE FROM bookings WHERE id = :id")
    suspend fun deleteBookingById(id: Int)

    @Query("SELECT * FROM wishlist ORDER BY timestamp DESC")
    fun getWishlistItems(): Flow<List<WishlistEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWishlist(item: WishlistEntity)

    @Query("DELETE FROM wishlist WHERE itemName = :itemName")
    suspend fun deleteWishlistItemByName(itemName: String)

    @Query("SELECT EXISTS(SELECT 1 FROM wishlist WHERE itemName = :itemName LIMIT 1)")
    fun isWishlisted(itemName: String): Flow<Boolean>
}

@Database(entities = [BookingEntity::class, WishlistEntity::class], version = 1, exportSchema = false)
abstract class PlayWithPurposeDatabase : RoomDatabase() {
    abstract fun playDao(): PlayDao

    companion object {
        @Volatile
        private var INSTANCE: PlayWithPurposeDatabase? = null

        fun getDatabase(context: Context): PlayWithPurposeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlayWithPurposeDatabase::class.java,
                    "play_with_purpose_db"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class PlayRepository(private val playDao: PlayDao) {
    val allBookings: Flow<List<BookingEntity>> = playDao.getAllBookings()
    val wishlistItems: Flow<List<WishlistEntity>> = playDao.getWishlistItems()

    suspend fun insertBooking(booking: BookingEntity) {
        playDao.insertBooking(booking)
    }

    suspend fun deleteBooking(id: Int) {
        playDao.deleteBookingById(id)
    }

    suspend fun addWishlist(item: WishlistEntity) {
        playDao.insertWishlist(item)
    }

    suspend fun removeWishlistByName(itemName: String) {
        playDao.deleteWishlistItemByName(itemName)
    }

    fun isWishlisted(itemName: String): Flow<Boolean> = playDao.isWishlisted(itemName)
}
