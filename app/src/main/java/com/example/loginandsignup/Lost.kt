package com.example.loginandsignup

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginandsignup.Adapter.MyAdapter
import com.example.loginandsignup.Models.Item
import com.example.loginandsignup.Models.ItemViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Lost.newInstance] factory method to
 * create an instance of this fragment.
 */

private lateinit var viewModel : ItemViewModel
private lateinit var itemRecyclerView: RecyclerView
lateinit var adapter: MyAdapter
private lateinit var viewHolder: MyAdapter.MyViewHolder
var items = ArrayList<Item>()

class Lost : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lost, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Found.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Found().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemRecyclerView = view.findViewById(R.id.recyclerView)
        itemRecyclerView.layoutManager = LinearLayoutManager(context)
        itemRecyclerView.setHasFixedSize(true)
        adapter = MyAdapter(requireContext(), items, this)
        itemRecyclerView.adapter = adapter
        createDummyItemList()


        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        viewModel.allItems.observe(viewLifecycleOwner, Observer {

            adapter.updateItemList(it)

        })

    }

    private fun createDummyItemList() {
        for(i in 1..100){
            items.add(Item("Item"+i))
        }
    }
    fun onItemItemClicked(position : Int){
        val intent = Intent(this@Lost.requireContext(),LostDes::class.java)
        intent.putExtra("heading", items[position].heading)
        intent.putExtra("description", items[position].description)
        intent.putExtra("contact", items[position].contact)
        intent.putExtra("image", items[position].image)
        startActivity(intent)
    }
}
