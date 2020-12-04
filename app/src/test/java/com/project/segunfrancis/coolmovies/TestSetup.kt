package com.project.segunfrancis.coolmovies

import android.content.Context
import androidx.room.Room
import com.project.segunfrancis.coolmovies.data.local.db.MovieDatabase
import com.project.segunfrancis.coolmovies.data.model.Result

/**
 * Created by SegunFrancis
 */
object TestSetup {

    fun database(context: Context): MovieDatabase {
        return Room.inMemoryDatabaseBuilder(context, MovieDatabase::class.java).build()
    }

    fun result(): Result {
        return Result(
            adult = false,
            video = false,
            vote_average = 7.8,
            vote_count = 3426,
            overview = "Fusce ac felis sit amet ligula pharetra condimentum. Curabitur a felis in nunc fringilla tristique. Duis vel nibh at velit scelerisque suscipit.",
            poster_path = "https://github.com",
            popularity = 3.4,
            backdrop_path = "https://github.com",
            original_language = "eng",
            title = "Clash of the titans",
            original_title = "Clash of the titans",
            release_date = "2009-6-7",
            id = 234,
            genre_ids = listOf(2, 3, 4)
        )
    }

    const val responseBody: String = "{\n" +
            "  \"page\": 1,\n" +
            "  \"results\": [\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/hW7K0B5Yr3v499SrWQO71ShGtWa.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        80\n" +
            "      ],\n" +
            "      \"id\": 311,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Once Upon a Time in America\",\n" +
            "      \"overview\": \"A former Prohibition-era Jewish gangster returns to the Lower East Side of Manhattan over thirty years later, where he once again must confront the ghosts and regrets of his old life.\",\n" +
            "      \"popularity\": 145.951,\n" +
            "      \"poster_path\": \"/r31djHVfrGqOhTan4ov6HTCA7lr.jpg\",\n" +
            "      \"release_date\": \"1984-05-23\",\n" +
            "      \"title\": \"Once Upon a Time in America\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.5,\n" +
            "      \"vote_count\": 3301\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/bx326cwBtDsfDcnTgFlK5dXkyaC.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        10402,\n" +
            "        18,\n" +
            "        10749\n" +
            "      ],\n" +
            "      \"id\": 630566,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Clouds\",\n" +
            "      \"overview\": \"Young musician Zach Sobiech discovers his cancer has spread, leaving him just a few months to live. With limited time, he follows his dream and makes an album, unaware that it will soon be a viral music phenomenon.\",\n" +
            "      \"popularity\": 134.708,\n" +
            "      \"poster_path\": \"/2YvT3pdGngzpbAuxamTz4ZlabnT.jpg\",\n" +
            "      \"release_date\": \"2020-10-09\",\n" +
            "      \"title\": \"Clouds\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.5,\n" +
            "      \"vote_count\": 432\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/bh6yfB6I1N1WuMiX6K5HxGVZnja.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        35,\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": 42269,\n" +
            "      \"original_language\": \"it\",\n" +
            "      \"original_title\": \"C'eravamo tanto amati\",\n" +
            "      \"overview\": \"Gianni, Nicola and Antonio become close friends in 1944 while fighting the Nazis. After the end of the war, full of illusions, they settle down. The movie is a the story of the life of these three idealists and how they deal with the inevitable disillusionments of life.\",\n" +
            "      \"popularity\": 5.582,\n" +
            "      \"poster_path\": \"/zGGWYpiKNwjpKxelPxOMqJnUgDs.jpg\",\n" +
            "      \"release_date\": \"1974-12-21\",\n" +
            "      \"title\": \"We All Loved Each Other So Much\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.5,\n" +
            "      \"vote_count\": 289\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/re3ZvlKJg04iLpLRf1xTKHS2wLU.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        16,\n" +
            "        12,\n" +
            "        35,\n" +
            "        10751,\n" +
            "        14,\n" +
            "        10402,\n" +
            "        28,\n" +
            "        18\n" +
            "      ],\n" +
            "      \"id\": 537061,\n" +
            "      \"original_language\": \"en\",\n" +
            "      \"original_title\": \"Steven Universe: The Movie\",\n" +
            "      \"overview\": \"Two years after the events of \\\"Change Your Mind\\\", Steven (now 16 years old) and his friends are ready to enjoy the rest of their lives peacefully. However, all of that changes when a new sinister Gem arrives, armed with a giant drill that saps the life force of all living things on Earth. In their biggest challenge ever, the Crystal Gems must work together to save all organic life on Earth within 48 hours.\",\n" +
            "      \"popularity\": 84.973,\n" +
            "      \"poster_path\": \"/8mRgpubxHqnqvENK4Bei30xMDvy.jpg\",\n" +
            "      \"release_date\": \"2019-09-02\",\n" +
            "      \"title\": \"Steven Universe: The Movie\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.4,\n" +
            "      \"vote_count\": 508\n" +
            "    },\n" +
            "    {\n" +
            "      \"adult\": false,\n" +
            "      \"backdrop_path\": \"/zoVeIgKzGJzpdG6Gwnr7iOYfIMU.jpg\",\n" +
            "      \"genre_ids\": [\n" +
            "        18,\n" +
            "        10749\n" +
            "      ],\n" +
            "      \"id\": 11216,\n" +
            "      \"original_language\": \"it\",\n" +
            "      \"original_title\": \"Nuovo Cinema Paradiso\",\n" +
            "      \"overview\": \"A filmmaker recalls his childhood, when he fell in love with the movies at his village's theater and formed a deep friendship with the theater's projectionist.\",\n" +
            "      \"popularity\": 16.374,\n" +
            "      \"poster_path\": \"/8SRUfRUi6x4O68n0VCbDNRa6iGL.jpg\",\n" +
            "      \"release_date\": \"1988-11-17\",\n" +
            "      \"title\": \"Cinema Paradiso\",\n" +
            "      \"video\": false,\n" +
            "      \"vote_average\": 8.5,\n" +
            "      \"vote_count\": 2630\n" +
            "    }\n" +
            "  ],\n" +
            "  \"total_pages\": 403,\n" +
            "  \"total_results\": 8054\n" +
            "}"
}