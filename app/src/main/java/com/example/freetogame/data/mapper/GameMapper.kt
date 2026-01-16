package com.example.freetogame.data.mapper

import com.example.freetogame.data.remote.dto.GameDetailDto
import com.example.freetogame.data.remote.dto.GameDto
import com.example.freetogame.data.remote.dto.ScreenshotDto
import com.example.freetogame.data.remote.dto.SystemRequirementsDto
import com.example.freetogame.domain.model.Game
import com.example.freetogame.domain.model.GameDetail
import com.example.freetogame.domain.model.Screenshot
import com.example.freetogame.domain.model.SystemRequirements

fun GameDto.toGame(): Game {
    return Game(
        id = id,
        title = title,
        thumbnail = thumbnail,
        shortDescription = shortDescription,
        genre = genre
    )
}

fun GameDetailDto.toGameDetail(): GameDetail {
    return GameDetail(
        id = id,
        title = title,
        thumbnail = thumbnail,
        description = description,
        genre = genre,
        platform = platform,
        publisher = publisher,
        developer = developer,
        releaseDate = releaseDate,
        gameUrl = gameUrl,
        minimumSystemRequirements = minimumSystemRequirements?.toSystemRequirements(),
        screenshots = screenshots.map { it.toScreenshot() }
    )
}

fun SystemRequirementsDto.toSystemRequirements(): SystemRequirements {
    return SystemRequirements(
        os = os,
        processor = processor,
        memory = memory,
        graphics = graphics,
        storage = storage
    )
}

fun ScreenshotDto.toScreenshot(): Screenshot {
    return Screenshot(
        id = id,
        image = image
    )
}
