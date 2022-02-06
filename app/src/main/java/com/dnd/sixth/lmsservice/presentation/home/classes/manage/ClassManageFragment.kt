package com.dnd.sixth.lmsservice.presentation.home.classes.manage

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentClassManageBinding
import com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter.ClassAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.home.classes.manage.making.MakeClassActivity
import com.dnd.sixth.lmsservice.presentation.listner.OnRecyclerItemClickListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassManageFragment : BaseFragment<FragmentClassManageBinding, ClassManageViewModel>(),
    View.OnClickListener, OnRecyclerItemClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_class_manage
    override val viewModel: ClassManageViewModel by viewModel()

    private var classAdapter: ClassAdapter? = null

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
        
    }

    private fun initView() {
        with(binding) {
            classAddBtn.setOnClickListener(this@ClassManageFragment)

            classAdapter = ClassAdapter(this@ClassManageFragment) // 수업 리사이클러뷰 어댑터
            with(classRecyclerView) {
                adapter = classAdapter // 어댑터 적용
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.class_add_btn -> {
                startActivity(Intent(requireContext(), MakeClassActivity::class.java))
            }
        }
    }

    override fun onClick(resId: Int) {
        when (resId) {
            R.id.more_btn -> {
                val dialogView =
                    layoutInflater.inflate(R.layout.layout_edit_delete_bottom_sheet, null)
                val moreDialog = BottomSheetDialog(requireContext()).apply {

                    setContentView(dialogView)
                }

                moreDialog.show()
            }
            R.id.class_btn -> {

            }
        }
    }


}