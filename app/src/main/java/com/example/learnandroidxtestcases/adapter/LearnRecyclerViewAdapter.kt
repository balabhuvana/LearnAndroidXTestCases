package com.example.learnandroidxtestcases.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.learnandroidxtestcases.R
import kotlinx.android.synthetic.main.sample_list_row.view.*


class LearnRecyclerViewAdapter(val context: Context, val myDataset: ArrayList<String>) :
    RecyclerView.Adapter<LearnRecyclerViewAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val linearLayout: LinearLayout) : RecyclerView.ViewHolder(linearLayout) {
        var nameTextView: TextView? = null
        var btnSwitch: SwitchCompat? = null

        init {
            nameTextView = linearLayout.tvName
            btnSwitch = linearLayout.btnSwitch
        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LearnRecyclerViewAdapter.MyViewHolder {
        // create a new view
        val linearLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.sample_list_row, parent, false) as LinearLayout
        // set the view's size, margins, paddings and layout parameters
        return MyViewHolder(linearLayout)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.nameTextView?.text = myDataset[position]
        holder.nameTextView?.setOnClickListener {
            Toast.makeText(context, "" + myDataset[position], Toast.LENGTH_LONG).show()
        }
        if (position == 5) {
            holder.btnSwitch?.visibility = View.VISIBLE

            holder.btnSwitch?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
                if (isChecked) { // The toggle is enabled
                    Toast.makeText(context, "True" + myDataset[position], Toast.LENGTH_LONG).show()
                } else { // The toggle is disabled
                    Toast.makeText(context, "False" + myDataset[position], Toast.LENGTH_LONG).show()
                }
            })
        } else {
            holder.btnSwitch?.visibility = View.INVISIBLE
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size
}