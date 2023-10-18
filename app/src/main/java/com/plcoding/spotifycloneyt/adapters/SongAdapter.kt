package com.plcoding.spotifycloneyt.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.plcoding.spotifycloneyt.R
import com.plcoding.spotifycloneyt.data.entities.Song
import kotlinx.android.synthetic.main.list_item.view.*
import javax.inject.Inject

class SongAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>(){

    class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

// diff util - tool which calculates differences once we calculate update the list
    // it will make update process of Recycler View More efficient

    private val diffCallback = object : DiffUtil.ItemCallback<Song>(){
        // the below two overrided methods are (ctrl +i)
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.mediaId == newItem.mediaId
        }
        //in the below code we compare hash codes of old and new , to find if they are exact same or not
        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var songs: List<Song>
    get()=differ.currentList
    set(value) = differ.submitList(value)

    //below 3 are methods of Recycler View(Ctrl + i)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
      // we will load the data of each song in the actual items of the REcycler View, so we set the UI accordingly
        val song = songs[position]
        holder.itemView.apply {
            tvPrimary.text= song.title
            tvSecondary.text =song.subtitle
            glide.load(song.imageUrl).into(ivItemImage)

            setOnItemClickListener {
                onItemClickListener?.let{click->
                    click(song)
                }
            }


        }
    }
    //we are creating a
    private var onItemClickListener: ((Song) -> Unit)? = null

    fun setOnItemClickListener(listener: (Song) -> Unit){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}