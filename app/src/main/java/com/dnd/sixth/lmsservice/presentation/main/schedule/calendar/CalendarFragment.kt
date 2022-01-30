package com.dnd.sixth.lmsservice.presentation.main.schedule.calendar

import android.content.Intent
import android.view.View
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.ActivityCalendarBinding
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.main.schedule.add.ScheduleAddActivity
import com.dnd.sixth.lmsservice.presentation.main.schedule.edit.ScheduleEditDialogFragment
import com.dnd.sixth.lmsservice.presentation.utility.ToolTipManager
import com.tomergoldst.tooltips.ToolTip
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalendarFragment : BaseFragment<ActivityCalendarBinding, CalendarViewModel>(),
    View.OnClickListener {
    override val layoutResId: Int
        get() = R.layout.activity_calendar
    override val viewModel: CalendarViewModel by viewModel()
    private var toolTipManger = ToolTipManager() // 툴팁 매니저

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData()
        initView()
        ScheduleEditDialogFragment().show(parentFragmentManager, "")
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }


    private fun initView() {
        with(binding) {
            scheduleAddFab.setOnClickListener(this@CalendarFragment)
        }
    }

    override fun onResume() {
        super.onResume()

        // 최초로 화면이 보여지는 경우 툴팁을 보여준다.
        toolTipManger.displayToolTip(
            binding.toolbar,
            binding.mainContainer,
            getString(R.string.calendar_progress_switch_tooltip),
            ToolTip.POSITION_BELOW,
            ToolTip.ALIGN_RIGHT
        )
    }

    override fun onClick(v: View?) {
        toolTipManger.dismissAll() // 뷰 클릭시 툴팁 해제

        when(v?.id) {
            R.id.schedule_add_fab -> startActivity(Intent(requireContext(), ScheduleAddActivity::class.java))
        }
    }

}