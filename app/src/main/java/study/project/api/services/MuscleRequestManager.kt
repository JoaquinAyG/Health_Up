package study.project.api.services

import study.project.api.client.API_MUSCLE_URL
import study.project.api.client.FORMAT_JSON
import study.project.api.models.responses.MuscleResponse
import java.net.URL

class MuscleRequestManager : ApiRequestManager<MuscleResponse> {
    override fun getId(id: Int): MuscleResponse.Results {
        return getUrlObject(
            URL("$API_MUSCLE_URL$id/$FORMAT_JSON"),
            MuscleResponse.Results::class.java
        )
    }

    override fun getAll(): MutableCollection<MuscleResponse> {
        val muscleList = mutableListOf<MuscleResponse>()
        var muscleResponse = MuscleResponse(null, API_MUSCLE_URL + FORMAT_JSON, null, arrayListOf())
        do {
            muscleResponse = getUrlObject(URL(muscleResponse.next), MuscleResponse::class.java)
            muscleList.add(muscleResponse)
        } while (muscleResponse.next != null)
        return muscleList
    }
}