package de.syntaxinstitut.project_app.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.project_app.R
import de.syntaxinstitut.project_app.data.datamodels.Blog
import de.syntaxinstitut.project_app.databinding.ActivityMainBinding

class BlogAdapter():RecyclerView.Adapter<BlogAdapter.ItemViewHolder>() {

private var dataset = listOf<Blog>()


    private lateinit var binding: ActivityMainBinding

    // Inhalt des Viewholders
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val card : ConstraintLayout = view.findViewById(R.id.blog_layout)
        val header : TextView = view.findViewById(R.id.tv_blog_number)
        val Titel : TextView = view.findViewById(R.id.tv_Titel)
        val subTitel : TextView = view.findViewById(R.id.tv_SubTitel)
        val contentTitel : TextView = view.findViewById(R.id.tv_content_titel)
        val content : TextView = view.findViewById(R.id.tv_content)
        val iconImg : ImageView = view.findViewById(R.id.iv_icon)
        val itemImg : ImageView = view.findViewById(R.id.iv_titel_Image)

    }


    // VIEWHOLDER

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        // das itemLayout wird gebaut

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.blog_item, parent, false)

        // und in einem ViewHolder zurückgegeben

        return ItemViewHolder(adapterLayout)
    }

    fun submitList(list: List<Blog>){
        dataset = list
        notifyDataSetChanged()
    }

// BEFÜLLEN DES VIEWHOLDERS
    // hier findet der Recyclingprozess statt
    // die vom ViewHolder bereitgestellten Parameter werden verändert

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        holder.header.text = item.blog_number
        holder.Titel.text = item.titel
        holder.subTitel.text = item.subTitel
        holder.contentTitel.text = item.content_titel
        holder.content.text = item.content
        item.icon?.let { holder.iconImg.setImageResource(it) }
        item.titel_Image?.let { holder.itemImg.setImageResource(it) }



    }

    // damit der LayoutManager weiß wie lang die Liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }



}