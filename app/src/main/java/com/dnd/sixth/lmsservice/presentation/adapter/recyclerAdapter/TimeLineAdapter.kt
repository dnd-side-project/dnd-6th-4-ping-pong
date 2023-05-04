package com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.data.model.testEntity.TestClassEntity
import com.dnd.sixth.lmsservice.databinding.ViewholderTimelineItemBinding
import com.dnd.sixth.lmsservice.domain.entity.DailyClassEntity
import com.dnd.sixth.lmsservice.presentation.comment.CommentActivity
import com.dnd.sixth.lmsservice.presentation.comment.check.CheckCommentActivity
import com.dnd.sixth.lmsservice.presentation.lesson.progress.ClassProgressActivity
import com.github.vipulasri.timelineview.TimelineView

//리사이클러뷰 뷰홀더 안에 타임라인 컴포넌트를 추가할 예정
class TimeLineAdapter(var onItemClicked : (TextView) -> Unit?) : RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder>(){

    //뿌려줄 데이터 리스트 (액티비티에서 전달 받을 예정)
    var timeLineList : List<DailyClassEntity> = listOf()

    override fun getItemViewType(position: Int): Int {
        return TimelineView.getTimeLineViewType(position, itemCount)
    }
    private var context : Context? = null

    fun contextAdd(context : Context){
        this.context = context
    }


    inner class TimeLineViewHolder(
        private val binding : ViewholderTimelineItemBinding,
        private val viewType: Int
    ) : RecyclerView.ViewHolder(binding.root){

        var mTimelineView : Unit = binding.timeline.initLine(viewType)






        fun bindData(data : DailyClassEntity) = with(binding){
            timelineDate.text =  data.classDays.toString() + ". 토"
            timelineClassNumber.text = "${data.classOrder}회차 수업"
            timelineClassTime.text = data.startTime
            timelineAddress.text = data.place
            mTimelineView
            if(context == null){
                openCommentBtn.visibility = GONE
                openFeedbackBtn.visibility = GONE
            }

            //피드백 남기기 버튼 리스너
            openFeedbackBtn.setOnClickListener {
                onItemClicked(openFeedbackBtn)
            }
            openCommentBtn.setOnClickListener {
                var intent = Intent(context, CheckCommentActivity::class.java)
                startActivity(context!!, intent, null)
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


