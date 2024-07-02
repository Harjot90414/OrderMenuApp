package com.harjot.ordermenuapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.harjot.ordermenuapp.databinding.LayoutOrderBinding
import com.harjot.ordermenuapp.databinding.LayoutShowAddItemsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class OrderFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private  val TAG = "OrderFragment"
    private var param1: String? = null
    private var param2: String? = null
    lateinit var Binding : LayoutShowAddItemsBinding
    lateinit var bottomNav: BottomNav
    var orderList = arrayListOf<MenuModel>()
    lateinit var adapter: OrderAdpater
    lateinit var menuAdapter: ArrayAdapter<MenuModel>
    lateinit var binding: LayoutOrderBinding
    var position = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNav= activity as BottomNav
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutOrderBinding.inflate(layoutInflater)
        binding.listOrderitem.layoutManager = LinearLayoutManager(bottomNav)
        adapter = OrderAdpater(orderList, object : OrderClickInterface {
            override fun addCounter(position: Int) {
//                var price= bottomNav.arraylist[position].price
                if (orderList[position].counter < orderList[position].quantity) {
                    orderList[position].counter = orderList[position].counter + 1
                    adapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(bottomNav, "Maximum Quantity Reached", Toast.LENGTH_SHORT).show()
                }
            }

            override fun removeCounter(position: Int) {
                if(orderList[position].counter <= 1){
                    orderList.removeAt(position)
                }
                else{
                    orderList[position].counter = orderList[position].counter-1
                }
                adapter.notifyDataSetChanged()
            }
        })

        binding.listOrderitem.adapter = adapter
        menuAdapter = ArrayAdapter(bottomNav, android.R.layout.simple_list_item_1,bottomNav.arraylist)
        binding.spnOrder.adapter = menuAdapter
        binding.spnOrder.setOnItemSelectedListener(object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                position = p2
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                position = -1
            }
        })
        binding.btnAdd.setOnClickListener{

//            if(bottomNav.arraylist[position] == orderList[position] && position>-1){
//                orderList[position].quantity = orderList[position].quantity+1
//            }
            if(position >-1)
            {
                orderList.add(bottomNav.arraylist[position])
                Log.e(TAG,"orderList ${orderList.size}")
                menuAdapter.notifyDataSetChanged()
            }

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Binding = LayoutShowAddItemsBinding.inflate(layoutInflater)

    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}