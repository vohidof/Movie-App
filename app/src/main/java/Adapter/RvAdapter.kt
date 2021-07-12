package Adapter

import Cache.MySharedPreference
import Model.RvModel
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.EditMovie
import com.example.movieapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_rv.view.*

class RvAdapter(var context: Context, var list: ArrayList<RvModel>) :
    RecyclerView.Adapter<RvAdapter.MyViewHolder>() {

    inner class MyViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(rvModel: RvModel, position: Int) {
            itemView.txt_title.text = rvModel.title
            itemView.txt_actors.text = rvModel.actor
            itemView.txt_date.text = rvModel.date
            itemView.animation = AnimationUtils.loadAnimation(context, R.anim.anim1)

            itemView.btn_edit.setOnClickListener {
                val intent = Intent(context, EditMovie::class.java)
                intent.putExtra("model", rvModel)
                intent.putExtra("position", position)
                context.startActivity(intent)
            }

            itemView.btn_delete.setOnClickListener {
                MySharedPreference.init(context)

                MySharedPreference.objectString = list

                notifyDataSetChanged()

                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position], position)

    }

    override fun getItemCount(): Int = list.size

}