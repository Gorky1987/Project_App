package de.syntaxinstitut.project_app.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.project_app.R
import de.syntaxinstitut.project_app.data.hView_Item
import de.syntaxinstitut.project_app.databinding.ActivityMainBinding


class hView_Adapter(
    private var dataset: List<hView_Item>,
    val navigationFun : (Int) -> Unit) : RecyclerView.Adapter<hView_Adapter.ItemViewHolder>() {

    private lateinit var binding: ActivityMainBinding

    // Inhalt des Viewholders
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val card : ConstraintLayout = view.findViewById(R.id.Layout_Card)
        val header : TextView = view.findViewById(R.id.tv_header)
        val detail : TextView = view.findViewById(R.id.tv_detail)
        val logoImg : ImageView = view.findViewById(R.id.logo_img)
        val itemImg : ImageView = view.findViewById(R.id.item_img)

    }

    fun submitList (list: List<hView_Item>){
        dataset = list
        notifyDataSetChanged()
        notifyItemInserted(0)
    }


    // VIEWHOLDER

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        // das itemLayout wird gebaut

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.horizontal_item_layout, parent, false)

        // und in einem ViewHolder zurückgegeben

        return ItemViewHolder(adapterLayout)
    }


// BEFÜLLEN DES VIEWHOLDERS
    // hier findet der Recyclingprozess statt
    // die vom ViewHolder bereitgestellten Parameter werden verändert

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.card.setOnClickListener {

        navigationFun(item.id)

        }

        holder.header.text = item.header_txt
        holder.detail.text = item.detail_txt
        holder.logoImg.setImageResource(item.logo_img)
        holder.itemImg.setImageResource(item.item_img)


    }

    // damit der LayoutManager weiß wie lang die Liste ist
    override fun getItemCount(): Int {
        return dataset.size
    }



}