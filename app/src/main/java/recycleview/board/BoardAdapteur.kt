package recycleview.board

import Player
import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sentance.yadi.R

class BoardAdapter : ListAdapter<Player, BoardAdapter.MyViewHolder>(MyDifUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.board_item, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val temp = getItem(position)
        holder.apply {
            name.text = temp.name
            txt.text = "*******"
            challenge.text = temp.challenge
            score.text = temp.score.toString()
            when(position){
                0 -> {
                    layout.setBackgroundColor(Color.rgb(218,165,32))
                    ic.visibility = View.VISIBLE
                }
                1 -> {
                    layout.setBackgroundColor(Color.rgb(192,192,192))
                    ic.visibility = View.VISIBLE
                }
                2 -> {
                    layout.setBackgroundColor(Color.rgb(204,102,51))
                    ic.visibility = View.VISIBLE
                }

            }
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.board_name_tv)
        var layout = itemView.findViewById<ConstraintLayout>(R.id.board_layout)
        var txt = itemView.findViewById<TextView>(R.id.board_txt_tv)
        var challenge = itemView.findViewById<TextView>(R.id.board_challenge_name_tv)
        var score = itemView.findViewById<TextView>(R.id.board_score_tv)
        var ic = itemView.findViewById<ImageView>(R.id.board_ic)
    }

}

class MyDifUtil : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
        return oldItem == newItem
    }
}