package gr.repo.kincart.models


import com.google.gson.annotations.SerializedName

data class CaseStudy(
    @SerializedName("app_store_url")
    val appStoreUrl: String,
    @SerializedName("client")
    val client: String,
    @SerializedName("hero_image")
    val heroImage: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_enterprise")
    val isEnterprise: Boolean,
    @SerializedName("sections")
    val sections: List<Section>,
    @SerializedName("teaser")
    val teaser: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("vertical")
    val vertical: String
)