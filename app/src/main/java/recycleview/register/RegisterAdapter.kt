package recycleview.register

import Player
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sentance.yadi.R

class RegisterAdapter(val deletable : Boolean) : ListAdapter<Player, RegisterAdapter.MyViewHolder>(MyDifUtilone()) {
    lateinit var onItemDelete : (Player) -> Unit
    lateinit var onClick : (Player) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: RegisterAdapter.MyViewHolder, position: Int) {
        val temp = getItem(position)
        holder.apply {
            name.text = temp.name
            if(deletable)
            deleteIv.setOnClickListener { onItemDelete(temp)}
            else{
                deleteIv.visibility = View.GONE
            }
            challenge.text = temp.challenge
            text.text = "*******************"
            if(!temp.textInLoop){
                card.setCardBackgroundColor(Color.LTGRAY)
            }
            card.setOnClickListener { onClick(temp) }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.player_item_card_name_tv)
        var challenge = itemView.findViewById<TextView>(R.id.player_item_card_challenge_tv)
        var text = itemView.findViewById<TextView>(R.id.player_item_card_txt_tv)
        var deleteIv = itemView.findViewById<ImageView>(R.id.player_item_card_delete_iv)
        var card = itemView.findViewById<CardView>(R.id.player_card)
    }

}

class MyDifUtilone : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.equals(newItem)
    }
}