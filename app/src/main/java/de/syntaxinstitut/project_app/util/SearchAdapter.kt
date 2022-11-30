package de.syntaxinstitut.project_app.util

import de.syntaxinstitut.project_app.data.datamodels.GymSearch


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import de.syntaxinstitut.project_app.R
import de.syntaxinstitut.project_app.data.datamodels.Blog
import de.syntaxinstitut.project_app.databinding.ActivityMainBinding
import org.w3c.dom.Text

class SearchAdapter( private var dataset : List<GymSearch>):RecyclerView.Adapter<SearchAdapter.ItemViewHolder>() {




    private lateinit var binding: ActivityMainBinding

    // Inhalt des Viewholders
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
      //  val card : ConstraintLayout = view.findViewById(R.id.searchItemLayout)
        val titel : TextView = view.findViewById(R.id.tv_search_titel)
        val subTitel : TextView = view.findViewById(R.id.tv_search_subText)
        val address : TextView = view.findViewById(R.id.tv_search_adress)
        val itemImg : ImageView = view.findViewById(R.id.iv_search_img)
        val rating : TextView = view.findViewById(R.id.tv_rating)
    }


    // VIEWHOLDER

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        // das itemLayout wird gebaut

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_item, parent, false)

        // und in einem ViewHolder zurückgegeben

        return ItemViewHolder(adapterLayout)
    }

    fun submitList(list: List<GymSearch>){
        dataset = list
        notifyDataSetChanged()
    }

// BEFÜLLEN DES VIEWHOLDERS
    // hier findet der Recyclingprozess statt
    // die vom ViewHolder bereitgestellten Parameter werden verändert

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.titel.text = item.title
        holder.subTitel.text = item.operating_hours.toString()
        holder.address.text = item.address
        holder.rating.text = item.rating.toString()
        holder.itemImg.load(item.thumbnail)

    }

    // damit der LayoutManager weiß wie lang die Liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }



}