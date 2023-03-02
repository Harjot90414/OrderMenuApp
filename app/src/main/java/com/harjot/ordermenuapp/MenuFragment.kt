package com.harjot.ordermenuapp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.harjot.ordermenuapp.databinding.AddItemDialogBinding
import com.harjot.ordermenuapp.databinding.DelUpdDialogBinding
import com.harjot.ordermenuapp.databinding.FragmentMenuBinding
import com.harjot.ordermenuapp.databinding.LayoutMenuitemBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class MenuFragment : Fragment(), MenuClickInterface {

    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentMenuBinding
    lateinit var diaBinding : LayoutMenuitemBinding
    lateinit var adapter: MenuAdapter
    lateinit var bottomNav: BottomNav

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
        binding = FragmentMenuBinding.inflate(layoutInflater)
        diaBinding = LayoutMenuitemBinding.inflate(layoutInflater)
        adapter = MenuAdapter(bottomNav.arraylist, this)
        binding.listMenuItems.layoutManager = LinearLayoutManager(bottomNav)
        binding.listMenuItems.adapter = adapter


        binding.fabAdd.setOnClickListener {
            var dialogBinding = AddItemDialogBinding.inflate(layoutInflater)
            var dialog = Dialog(requireContext())
            dialog.setContentView(dialogBinding.root)
            dialog.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )

            dialogBinding.btnItemAdd.setOnClickListener {
                if (dialogBinding.etItemName.text.toString().isNullOrEmpty()) {
                    dialogBinding.etItemName.setError("Enter Item Name")
                } else if (dialogBinding.etItemPrice.text.toString().isNullOrEmpty()) {
                    dialogBinding.etItemPrice.setError("Enter Item Price")
                } else {
                    Toast.makeText(bottomNav, resources.getString(R.string.item_added), Toast.LENGTH_LONG).show()
                    bottomNav.arraylist.add(MenuModel(dialogBinding.etItemName.text.toString(), dialogBinding.etItemPrice.text.toString().toDouble()))

                    adapter.notifyDataSetChanged()
                    dialog.dismiss()

                }
            }
            dialog.show()
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun removeCliCK(menuModel: MenuModel, position: Int) {
        var dbinding = DelUpdDialogBinding.inflate(layoutInflater)
        var dialog = Dialog(requireContext())
        dialog.setContentView(dbinding.root)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dbinding.etUpdateItemName.setText(menuModel.name)
        dbinding.etUpdateItemPrice.setText(menuModel.price.toString())
        dbinding.btnUpdate.setOnClickListener{
            if(dbinding.etUpdateItemName.text.toString().isNullOrEmpty()){
                dbinding.etUpdateItemName.setError("Enter Update Item Name")
            }else if(dbinding.etUpdateItemPrice.text.toString().isNullOrEmpty()){
                dbinding.etUpdateItemPrice.setError("Enter Update  Item Price")
            }else{
                Toast.makeText(bottomNav, resources.getString(R.string.item_added), Toast.LENGTH_LONG).show()
                bottomNav.arraylist[position] = MenuModel(dbinding.etUpdateItemName.text.toString(),dbinding.etUpdateItemPrice.text.toString().toDouble())
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }
        }
        dbinding.btnDelete.setOnClickListener{
            bottomNav.arraylist.removeAt(position)
            adapter.notifyDataSetChanged()


            dialog.dismiss()
        }

        dialog.show()
    }
}