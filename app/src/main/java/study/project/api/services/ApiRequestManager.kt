package study.project.api.services

import study.project.api.models.responses.BaseResponse

interface ApiRequestManager<T: BaseResponse> {

    fun getId(id : Int): BaseResponse.Results
    fun getAll(): MutableCollection<T>

}