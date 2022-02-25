package com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dnd.sixth.lmsservice.databinding.ItemClassBinding
import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.presentation.base.BaseDiffUtil
import com.dnd.sixth.lmsservice.presentation.extensions.applyDowColor
import com.dnd.sixth.lmsservice.presentation.extensions.visibleViewListIfContain
import com.dnd.sixth.lmsservice.presentation.listner.OnRecyclerItemClickListener

class SubjectAdapter(
    var modelListDaily: MutableList<GeneralSubjectEntity>,
    val listener: OnRecyclerItemClickListener
) :
    RecyclerView.Adapter<SubjectAdapter.ClassViewHolder>() {


    inner class ClassViewHolder(val binding: ItemClassBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val classModel = modelListDaily[adapterPosition]


            with(binding) {
                setClassModel(classModel)

                moreBtn.setOnClickListener {
                    listener.onClick(it.id, adapterPosition)
                }
                classBtn.setOnClickListener {
                    listener.onClick(it.id, adapterPosition)
                }

                // 수업 요일을 보여줌
                classModel.classDayBit?.let {
                    visibleViewListIfContain(
                        it,
                        listOf<View>(monIcon, tueIcon, wedIcon, thurIcon, friIcon, satIcon, sunIcon)
                    )
                }

                // 수업 요일 색상 변경
                applyDowColor(
                    classModel.color.toInt(),
                    listOf<TextView>(monIcon, tueIcon, wedIcon, thurIcon, friIcon, satIcon, sunIcon)
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

    fun updateItem(newModelListDaily: List<GeneralSubjectEntity>) {
        val diffUtilCallback = BaseDiffUtil(modelListDaily, newModelListDaily)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback, true)

        modelListDaily.clear()
        modelListDaily.addAll(newModelListDaily)

        diffResult.dispatchUpdatesTo(this)
    }
}