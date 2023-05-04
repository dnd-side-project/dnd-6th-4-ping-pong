package com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.model.testEntity.TestHomeworkItem
import com.dnd.sixth.lmsservice.databinding.ItemHomeworkTimelineBinding
import com.github.vipulasri.timelineview.TimelineView

// 숙제관리 타임라인 형식리스트를 위한 어답터
class HomeworkManageAdapter : RecyclerView.Adapter<HomeworkManageAdapter.TimeLineViewHolder>(){

    val homework = mutableMapOf("영단어 Day01~02" to false,"영단어 Day02~03" to false,"영단어 Day03~04" to false )
    private var timeLineList : List<TestHomeworkItem> = listOf(
        TestHomeworkItem(1,"2021.03.02.토","2월 1회차",homework),
        TestHomeworkItem(1,"2021.03.03.토","2월 2회차",homework),
        TestHomeworkItem(1,"2021.03.04.토","2월 3회차",homework),
        TestHomeworkItem(1,"2021.03.05.토","2월 4회차",homework),
        TestHomeworkItem(1,"2021.03.06.토","2월 5회차",homework),
        TestHomeworkItem(1,"2021.03.07.토","2월 6회차",homework)
    )

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }


    inner class TimeLineViewHolder(
        private val binding : ItemHomeworkTimelineBinding,
        private val viewType: Int
    ) : RecyclerView.ViewHolder(binding.root){

        var mTimelineView : Unit = binding.timeline.initLine(viewType)



        fun bindData(data : TestHomeworkItem) = with(binding){
           var list : MutableList<String> = mutableListOf()

            homework.forEach{
                list.add(it.key)
            }
            timelineDate.text =  data.Date
            timelineClassNumber.text = "${data.numbering}"
            firstHomeworkCheck.text = list[0]
            secondHomeworkCheck.text = list[1]
            thirdHomeworkCheck.text = list[2]

            mTimelineView


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineViewHolder {
//       var view = ViewholderTimelineItemBinding.inflate(LayoutInflater.from(parent.context),parent)
//        return TimeLineViewHolder(view)

        val binding = DataBindingUtil.inflate<ItemHomeworkTimelineBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_homework_timeline_,
            parent,
            false
        )
        return TimeLineViewHolder(binding, viewType)
    }

    override fun onBindViewHolder(holder: TimeLineViewHolder, position: Int) {
        holder.bindData(timeLineList[position])
    }

    override fun getItemCount(): Int {
        return timeLineList.size
    }
}


