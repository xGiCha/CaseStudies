package gr.repo.kincart.models


import com.google.gson.annotations.SerializedName

data class Section(
    @SerializedName("body_elements")
    val bodyElements: List<Any>,
    @SerializedName("title")
    val title: Any
)