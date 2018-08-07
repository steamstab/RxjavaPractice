package com.mintdev.rxjavapractice

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mintdev.rxjavapractice.bean.MActivityItem
import com.mintdev.rxjavapractice.ui.ObservableSimpleActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_activity.view.*

class MainActivity : AppCompatActivity() {

    val mList = mutableListOf<MActivityItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mList.add(MActivityItem("基础实现", "Observable", ObservableSimpleActivity::class.java))

        mRecycler.layoutManager = LinearLayoutManager(this)
        mRecycler.adapter = MainAdapter()
        mRecycler.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
    }

    inner class MainAdapter : RecyclerView.Adapter<MainAdapter.ItemViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            return ItemViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_activity, parent, false))
        }

        override fun getItemCount(): Int = mList.size

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.fillData(mList[position])
        }

        inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun fillData(item: MActivityItem) {
                itemView.mTitleTv.text = item.title
                itemView.mDescTv.text = item.desc

                itemView.setOnClickListener {
                    startActivity(Intent(this@MainActivity, item.to_class))
                }
            }
        }
    }
}
