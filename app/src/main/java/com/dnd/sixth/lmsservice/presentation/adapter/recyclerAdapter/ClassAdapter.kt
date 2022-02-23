package com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dnd.sixth.lmsservice.databinding.ItemClassBinding
import com.dnd.sixth.lmsservice.domain.entity.GeneralSubjectEntity
import com.dnd.sixth.lmsservice.presentation.base.BaseDiffUtil
import com.dnd.sixth.lmsservice.presentation.extensions.visibleViewListIfContain
import com.dnd.sixth.lmsservice.presentation.listner.OnRecyclerItemClickListener

class ClassAdapter(
    var modelListDaily: List<GeneralSubjectEntity>,
    val listener: OnRecyclerItemClickListener
) :
    RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {


    inner class ClassViewHolder(val binding: ItemClassBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val classModel = modelListDaily[adapterPosition]

            // 수업 듣는 요일을 Binary 형태로 표현한 String (ex. 1110111)
            // 8bit 데이터의 공백은 0으로 채웁니다.
            val classDayBinaryString =
                String.format("%7s", classModel.classDayBit).replace("", "0")

            with(binding) {
                setClassModel(classModel)


                moreBtn.setOnClickListener {
                    listener.onClick(it.id, adapterPosition)
                }
                classBtn.setOnClickListener {
                    listener.onClick(it.id, adapterPosition)
                }


                // 수업 요일을 보여줌
                visibleViewListIfContain(
                    classDayBinaryString,
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

    fun updateItem(newModelListDaily: List<GeneralSubjectEntity>) {
        val diffUtilCallback = BaseDiffUtil(modelListDaily, newModelListDaily)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback, true)

        modelListDaily = newModelListDaily
        diffResult.dispatchUpdatesTo(this)
    }
}