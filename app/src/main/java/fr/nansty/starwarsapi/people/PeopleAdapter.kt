package fr.nansty.starwarsapi.people

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import fr.nansty.starwarsapi.R
import kotlinx.android.synthetic.main.item_people.view.*

class PeopleAdapter(private val peoples: List<People>,
                    private val peopleListener: PeopleAdapter.PeopleItemListener) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>(),
    View.OnClickListener {

    interface PeopleItemListener{
        fun onPeopleSelected(people: People)
        fun onPeopleDeleted(people: People)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardView = itemView.findViewById<CardView>(R.id.card_view)!!
        val peopleNameView = itemView.findViewById<TextView>(R.id.name)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_people, parent, false)
        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val people = peoples[position]
        with(holder){
            cardView.tag = people
            cardView.setOnClickListener(this@PeopleAdapter)
            peopleNameView.text = people.name
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.card_view -> peopleListener.onPeopleSelected(v.tag as People)
        }
    }
}