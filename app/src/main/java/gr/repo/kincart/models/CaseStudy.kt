package gr.repo.kincart.models


import com.google.gson.annotations.SerializedName

data class CaseStudy(
        @SerializedName("app_store_url")
        val appStoreUrl: String? = null,
        @SerializedName("client")
        val client: String? = null,
        @SerializedName("hero_image")
        val heroImage: String? = null,
        @SerializedName("id")
        val id: Int,
        @SerializedName("is_enterprise")
        val isEnterprise: Boolean? = false,
        @SerializedName("sections")
        val sections: List<Section>? = null,
        @SerializedName("teaser")
        val teaser: String? = null,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("vertical")
        val vertical: String? = null
)