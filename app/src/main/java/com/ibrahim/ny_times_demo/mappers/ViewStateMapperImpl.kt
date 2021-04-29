package com.ibrahim.ny_times_demo.mappers

import com.ibrahim.ny_times_demo.api.model.Media
import com.ibrahim.ny_times_demo.api.model.Result
import com.ibrahim.ny_times_demo.ui.fragments.models.PopularArticle
import com.ibrahim.ny_times_demo.util.empty
import javax.inject.Inject

class ViewStateMapperImpl @Inject constructor() : ViewStateMapper {
    override fun mapArticlesToViewState(results: List<Result>): List<PopularArticle> {
        return results.map { mapToArticles(it) }
    }

    private fun mapToArticles(result: Result) : PopularArticle{
        return with(result){
            PopularArticle(
                id = this.id,
                title = this.title,
                publish_data = this.publishedDate,
                url = this.url,
                byline = this.byline,
                imageUrl = getImageUrl(this.media)
            )
        }
    }

    private fun getImageUrl(media: List<Media>): String {
        return if (media.isNotEmpty()
            && media[0].type == "image"
            && !media[0].mediaMetadata.isNullOrEmpty()){
            return media[0].mediaMetadata[0].url
        }
        else
            String.empty
    }
}