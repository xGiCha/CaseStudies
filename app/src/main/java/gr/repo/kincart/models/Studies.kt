package gr.repo.kincart.models


import com.google.gson.annotations.SerializedName

data class Studies(
    @SerializedName("case_studies")
    val caseStudies: List<CaseStudy>
)