package study.project.api.client

import study.project.api.models.views.*
import study.project.api.services.*

object HealthApiManagerClient {

    val exerciseRequestManager: ExerciseRequestManager by lazy { ExerciseRequestManager() }
    val exerciseImageRequestManager: ExerciseImageRequestManager by lazy { ExerciseImageRequestManager() }
    val exerciseCommentRequestManager: ExerciseCommentRequestManager by lazy { ExerciseCommentRequestManager() }
    val exerciseInfoRequestManager: ExerciseInfoRequestManager by lazy { ExerciseInfoRequestManager() }
    val exerciseCategoryRequestManager: ExerciseCategoryRequestManager by lazy { ExerciseCategoryRequestManager() }
    val muscleRequestManager: MuscleRequestManager by lazy { MuscleRequestManager() }

    fun getAllExercises(): List<ExerciseView> {
        val exerciseList = mutableListOf<ExerciseView>()
        exerciseRequestManager.getAll()
            .forEach { it.results.forEach { exerciseList.add(ExerciseView().fromResult(it)) } }
        return exerciseList
    }

    fun getAllExerciseImages(): List<ExerciseImageView> {
        val exerciseImageList = mutableListOf<ExerciseImageView>()
        exerciseImageRequestManager.getAll().forEach {
            it.results.forEach {
                exerciseImageList.add(
                    ExerciseImageView().fromResult(it)
                )
            }
        }
        return exerciseImageList
    }

    fun getAllExerciseComments(): List<ExerciseCommentView> {
        val exerciseCommentList = mutableListOf<ExerciseCommentView>()
        exerciseCommentRequestManager.getAll().forEach {
            it.results.forEach {
                exerciseCommentList.add(
                    ExerciseCommentView().fromResult(it)
                )
            }
        }
        return exerciseCommentList
    }

    fun getAllMuscles(): List<MuscleView> {
        val muscleList = mutableListOf<MuscleView>()
        muscleRequestManager.getAll()
            .forEach { it.results.forEach { muscleList.add(MuscleView().fromResult(it)) } }
        return muscleList
    }

    fun getAllExerciseInfo(): List<ExerciseInfoView> {
        val exerciseInfoList = mutableListOf<ExerciseInfoView>()
        exerciseInfoRequestManager.getAll().forEach {
            it.results.forEach {
                exerciseInfoList.add(
                    ExerciseInfoView().fromResult(it)
                )
            }
        }
        return exerciseInfoList
    }

    fun getAllExerciseCategories(): List<ExerciseCategoryView> {
        val exerciseCategoryList = mutableListOf<ExerciseCategoryView>()
        exerciseCategoryRequestManager.getAll().forEach {
            it.results.forEach {
                exerciseCategoryList.add(
                    ExerciseCategoryView().fromResult(it)
                )
            }
        }
        return exerciseCategoryList
    }

    fun getExerciseInfoById(id: Int): ExerciseInfoView {
        return ExerciseInfoView().fromResult(exerciseInfoRequestManager.getId(id))
    }

    fun getExerciseById(id: Int): ExerciseView {
        return ExerciseView().fromResult(exerciseRequestManager.getId(id))
    }

    fun getExerciseImageById(id: Int): ExerciseImageView {
        return ExerciseImageView().fromResult(exerciseImageRequestManager.getId(id))
    }

    fun getExerciseCommentById(id: Int): ExerciseCommentView {
        return ExerciseCommentView().fromResult(exerciseCommentRequestManager.getId(id))
    }

    fun getExerciseCategoryById(id: Int): ExerciseCategoryView {
        return ExerciseCategoryView().fromResult(exerciseCategoryRequestManager.getId(id))
    }

    fun getMuscleById(id: Int): MuscleView {
        return MuscleView().fromResult(muscleRequestManager.getId(id))
    }

}