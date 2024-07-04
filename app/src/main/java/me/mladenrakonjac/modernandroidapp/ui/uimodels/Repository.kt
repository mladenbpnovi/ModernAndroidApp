package me.mladenrakonjac.modernandroidapp.ui.uimodels

data class Repository(
    var repositoryName: String,
    var repositoryOwner: String?,
    var numberOfStars: Int?,
    var hasIssues: Boolean = false
)
