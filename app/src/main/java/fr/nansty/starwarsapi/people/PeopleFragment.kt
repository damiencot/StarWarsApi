package fr.nansty.starwarsapi.people

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.nansty.starwarsapi.App
import fr.nansty.starwarsapi.Database
import fr.nansty.starwarsapi.R

class PeopleFragment : Fragment(), PeopleAdapter.PeopleItemListener {

    private lateinit var peoples: MutableList<People>
    private lateinit var database: Database
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PeopleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = App.database
        peoples = mutableListOf()
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_people, container, false)
        recyclerView = view.findViewById(R.id.people_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        peoples = database.getAllPeoples()
        adapter = PeopleAdapter(peoples, this)
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.fragment_people, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_create_people ->{
                showCreatePeopleDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showCreatePeopleDialog() {
        val createPeopleFragment = CreatePeopleFragment()
        createPeopleFragment.listener = object: CreatePeopleFragment.CreatePeopleListener{
            override fun onDialogPositiveClick(peopleName: String) {
                savePeople(People(peopleName))
            }

            override fun onDialogNegatibeClick() {
            }
        }
        createPeopleFragment.show(fragmentManager!!, "CreatePeopleFragment")
    }

    private fun savePeople(people: People) {
        if (database.createPeople(people)){
            peoples.add(people)
            adapter.notifyDataSetChanged()
        }else{
            Toast.makeText(context, "Could not create people", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onPeopleSelected(people: People) {
        TODO("Not yet implemented")
    }

    override fun onPeopleDeleted(people: People) {
        TODO("Not yet implemented")
    }

}