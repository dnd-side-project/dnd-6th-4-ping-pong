package com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.model.testEntity.TestClassEntity
import com.dnd.sixth.lmsservice.databinding.ViewholderTimelineItemBinding
import com.github.vipulasri.timelineview.TimelineView

//리사이클러뷰 뷰홀더 안에 타임라인 컴포넌트를 추가할 예정
class TimeLineAdapter(var onItemClicked : (TextView) -> Unit?) : RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>(){

    private var timeLineList : List<TestClassEntity> = listOf(
        TestClassEntity(1,"야스오","수학","2월2일(토)","6시 30분","소환사의 협곡", 5),
        TestClassEntity(2,"야스오","수학","2월3일(일)","6시 30분","소환사의 협곡", 6),
        TestClassEntity(3,"야스오","수학","2월4일(월)","6시 30분","소환사의 협곡", 7),
        TestClassEntity(4,"야스오","수학","2월5일(화)","6시 30분","소환사의 협곡", 8),
        TestClassEntity(5,"야스오","수학","2월6일()","6시 30분","소환사의 협곡", 9)
    )

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }


    inner class TimeLineViewHolder(
        private val binding : ViewholderTimelineItemBinding,
        private val viewType: Int
    ) : RecyclerView.ViewHolder(binding.root){

        var mTimelineView : Unit = binding.timeline.initLine(viewType)



        fun bindData(data : TestClassEntity) = with(binding){
            timelineDate.text =  data.classDate
            timelineClassNumber.text = "${data.number}회차 수업"
            timelineClassTime.text = data.classTime
            timelineAddress.text = data.address
            mTimelineView

            //피드백 남기기 버튼 리스너
            openFeedbackBtn.setOnClickListener {
                onItemClicked(openFeedbackBtn)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineViewHolder {
//       var view = ViewholderTimelineItemBinding.inflate(LayoutInflater.from(parent.context),parent)
//        return TimeLineViewHolder(view)

        val binding = DataBindingUtil.inflate<ViewholderTimelineItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.viewholder_timeline_item,
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


