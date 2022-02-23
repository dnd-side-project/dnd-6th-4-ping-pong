package com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dnd.sixth.lmsservice.data.response.User
import com.dnd.sixth.lmsservice.databinding.ItemClassBinding
import com.dnd.sixth.lmsservice.domain.entity.DailyClass
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.presentation.base.BaseDiffUtil
import com.dnd.sixth.lmsservice.presentation.extensions.visibleViewListIfContain
import com.dnd.sixth.lmsservice.presentation.listner.OnRecyclerItemClickListener

class ClassAdapter(var modelListDaily: List<SubjectEntity>, val listener: OnRecyclerItemClickListener) :
    RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {


    inner class ClassViewHolder(val binding: ItemClassBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val subjectModel = modelListDaily[adapterPosition]
            val dayOfWeekBinary = subjectModel.classDowBit

            with(binding) {
                setSubjectModel(subjectModel)
                dailyModel = DailyClass(0,0,"","","","","","","","",true,"",0b0001000)
                userModel = User(0,true,"")


                moreBtn.setOnClickListener {
                    listener.onClick(it.id, adapterPosition)
                }
                classBtn.setOnClickListener {
                    listener.onClick(it.id, adapterPosition)
                }

                // 수업 요일을 보여줌
                visibleViewListIfContain(
                    dayOfWeekBinary,
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

    override fun getItemCount(): Int = modelListDaily.size

    fun updateItem(newModelListDaily: List<SubjectEntity>) {
        val diffUtilCallback = BaseDiffUtil(modelListDaily, newModelListDaily)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback, true)

        modelListDaily = newModelListDaily
        diffResult.dispatchUpdatesTo(this)
    }
}