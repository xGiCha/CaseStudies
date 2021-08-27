package gr.repo.kincart.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import gr.repo.kincart.models.Studies

class MoviesTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun caseStudyToString(caseStudy: Studies): String {
        return gson.toJson(caseStudy)
    }

    @TypeConverter
    fun stringToCaseStudy(data: String): Studies {
        val listType = object : TypeToken<Studies>() {}.type
        return gson.fromJson(data, listType)
    }

}