package com.outthinking.newsmvvmex.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.outthinking.newsmvvmex.db.Converters
import java.io.Serializable

@Entity(
    tableName = "articles"
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    @TypeConverters(Converters::class)
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
):Serializable{
    override fun hashCode(): Int {
        var result = id.hashCode()
        if (content.isEmpty()) {
            result = 31 * result + content.hashCode()
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Article

        if (id != other.id) return false
        if (author != other.author) return false
        if (content != other.content) return false
        if (description != other.description) return false
        if (publishedAt != other.publishedAt) return false
        if (source != other.source) return false
        if (title != other.title) return false
        if (url != other.url) return false
        if (urlToImage != other.urlToImage) return false

        return true
    }
}