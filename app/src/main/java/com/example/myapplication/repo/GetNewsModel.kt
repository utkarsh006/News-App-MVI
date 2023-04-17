package com.example.myapplication.repo

import com.google.gson.annotations.SerializedName

data class GetNewsModel(
    @SerializedName("status"      ) var status     : String?             = null,
    @SerializedName("total_hits"  ) var totalHits  : Int?                = null,
    @SerializedName("page"        ) var page       : Int?                = null,
    @SerializedName("total_pages" ) var totalPages : Int?                = null,
    @SerializedName("page_size"   ) var pageSize   : Int?                = null,
    @SerializedName("articles"    ) var articles   : ArrayList<Articles> = arrayListOf(),
    @SerializedName("user_input"  ) var userInput  : UserInput?          = UserInput()
){
    data class Articles (
        @SerializedName("title"                    ) var title                  : String?  = null,
        @SerializedName("author"                   ) var author                 : String?  = null,
        @SerializedName("published_date"           ) var publishedDate          : String?  = null,
        @SerializedName("published_date_precision" ) var publishedDatePrecision : String?  = null,
        @SerializedName("link"                     ) var link                   : String?  = null,
        @SerializedName("clean_url"                ) var cleanUrl               : String?  = null,
        @SerializedName("excerpt"                  ) var excerpt                : String?  = null,
        @SerializedName("summary"                  ) var summary                : String?  = null,
        @SerializedName("rights"                   ) var rights                 : String?  = null,
        @SerializedName("rank"                     ) var rank                   : Int?     = null,
        @SerializedName("topic"                    ) var topic                  : String?  = null,
        @SerializedName("country"                  ) var country                : String?  = null,
        @SerializedName("language"                 ) var language               : String?  = null,
        @SerializedName("authors"                  ) var authors                : String?  = null,
        @SerializedName("media"                    ) var media                  : String?  = null,
        @SerializedName("is_opinion"               ) var isOpinion              : Boolean? = null,
        @SerializedName("twitter_account"          ) var twitterAccount         : String?  = null,
        @SerializedName("_score"                   ) var Score                  : String?  = null,
        @SerializedName("_id"                      ) var Id                     : String?  = null

    )
    data class UserInput (

        @SerializedName("lang"          ) var lang         : String?           = null,
        @SerializedName("not_lang"      ) var notLang      : String?           = null,
        @SerializedName("countries"     ) var countries    : ArrayList<String> = arrayListOf(),
        @SerializedName("not_countries" ) var notCountries : String?           = null,
        @SerializedName("page"          ) var page         : Int?              = null,
        @SerializedName("size"          ) var size         : Int?              = null,
        @SerializedName("sources"       ) var sources      : String?           = null,
        @SerializedName("not_sources"   ) var notSources   : ArrayList<String> = arrayListOf(),
        @SerializedName("topic"         ) var topic        : String?           = null,
        @SerializedName("from"          ) var from         : String?           = null

    )
}
