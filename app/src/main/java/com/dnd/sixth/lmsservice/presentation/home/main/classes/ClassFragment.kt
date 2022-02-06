package com.dnd.sixth.lmsservice.presentation.home.main.classes

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentClassBinding
import com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter.ClassAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.home.main.classes.create.ClassCreateActivity
import com.dnd.sixth.lmsservice.presentation.listner.OnRecyclerItemClickListener
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClassFragment : BaseFragment<FragmentClassBinding, ClassViewModel>(),
    View.OnClickListener, OnRecyclerItemClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_class
    override val viewModel: ClassViewModel by viewModel()

    private var classAdapter: ClassAdapter? = null

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화

    }

    private fun initView() {
        with(binding) {
            classAddBtn.setOnClickListener(this@ClassFragment)

            classAdapter = ClassAdapter(this@ClassFragment) // 수업 리사이클러뷰 어댑터
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
                startActivity(Intent(requireContext(), ClassCreateActivity::class.java))
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