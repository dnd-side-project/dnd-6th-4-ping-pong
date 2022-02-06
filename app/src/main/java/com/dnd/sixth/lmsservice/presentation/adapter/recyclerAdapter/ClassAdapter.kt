package com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.entity.ClassItem
import com.dnd.sixth.lmsservice.databinding.ItemClassBinding
import com.dnd.sixth.lmsservice.presentation.extensions.visibleViewListIfContain
import com.dnd.sixth.lmsservice.presentation.listner.OnRecyclerItemClickListener

class ClassAdapter(val listener: OnRecyclerItemClickListener) :
    RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {

    val testList = arrayListOf<ClassItem>(
        ClassItem("1", "백지성", "1", "고3", "영어", listOf("월", "수"), true),
        ClassItem("1", "김철수", "1", "고3", "영어", listOf("토"), false),
        ClassItem("1", "김철수", "1", "고3", "영어", listOf("토"), false),
    )
    val dayOfWeeks = listOf<String>("월", "화", "수", "목", "금", "토", "일")

    inner class ClassViewHolder(val binding: ItemClassBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val context = binding.root.context
            val classModel = testList[adapterPosition]
            val classDayOfWeeks = classModel.classDays

            with(binding) {
                model = classModel

                moreBtn.setOnClickListener {
                    listener.onClick(it.id)
                }
                classBtn.setOnClickListener {
                    listener.onClick(it.id)
                }

                // 수업 요일을 보여줌
                dayOfWeeks.visibleViewListIfContain(
                    classDayOfWeeks,
                    listOf<View>(monIcon, tueIcon, wedIcon, thurIcon, friIcon, satIcon, sunIcon)
                )

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val binding = ItemClassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClassViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = testList.size

}