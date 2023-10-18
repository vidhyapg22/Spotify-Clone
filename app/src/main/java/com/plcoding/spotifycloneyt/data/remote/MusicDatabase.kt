package com.plcoding.spotifycloneyt.data.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.plcoding.spotifycloneyt.data.entities.Song
import com.plcoding.spotifycloneyt.other.Constants.SONG_COLLECTION
import kotlinx.coroutines.tasks.await

class MusicDatabase {

    private val firestore =FirebaseFirestore.getInstance()
    private val songCollection = firestore.collection(SONG_COLLECTION)

    //function to get songs - we are creating syncronous function to makr a network call
    //so that song must not run on main thread

    suspend fun getAllSongs() : List<Song>{
        return try{
            songCollection.get().await().toObjects(Song::class.java)
        }catch (e:Exception){
            emptyList()
        }

    }

}