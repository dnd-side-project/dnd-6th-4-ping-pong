package com.dnd.sixth.lmsservice.presentation.home.main.classes

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnd.sixth.lmsservice.BuildConfig
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentClassBinding
import com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter.ClassAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.home.main.ClassHomeViewModel
import com.dnd.sixth.lmsservice.presentation.home.main.classes.create.ClassCreateActivity
import com.dnd.sixth.lmsservice.presentation.listner.OnRecyclerItemClickListener
import com.dnd.sixth.lmsservice.presentation.utility.UnitConverter
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.lang.IllegalStateException

class ClassFragment : BaseFragment<FragmentClassBinding, ClassViewModel>(),
    View.OnClickListener, OnRecyclerItemClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_class
    override val viewModel: ClassViewModel by viewModel()

    private var classAdapter: ClassAdapter? = null

    // 최상위 ViewTreeObserver (높이를 구하기 위한 변수)
    var viewTreeObserver: ViewTreeObserver? = null

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화

    }

    private fun initView() {
        with(binding) {
            classAddBtn.setOnClickListener(this@ClassFragment)

            classAdapter = ClassAdapter(
                viewModel?.classLiveDataList?.value!!,
                this@ClassFragment
            ) // 수업 리사이클러뷰 어댑터
            with(classRecyclerView) {
                adapter = classAdapter // 어댑터 적용
                layoutManager = LinearLayoutManager(requireContext())
            }

            // 수업 리스트가 변경됨에 따라 화면 크기 조절을 하기 위한 Observer
            viewModel?.classLiveDataList?.observe(this@ClassFragment) {
                if(viewModel?.hasClass() == true) { // 수업이 있다면
                    setClassHomeScrollViewHeight() // 수업 RecyclerView Item 사이즈에 맞게 HomeFragment의 Scroll 높이 재설정
                    binding.noClassContainer.visibility = View.GONE // '수업이 없어요' 화면 가리기
                } else { // 수업이 없다면
                    binding.noClassContainer.visibility = View.VISIBLE // '수업이 없어요' 화면 가리기
                }

                // 리사이클러뷰 갱신
                classAdapter?.updateItem(it)
            }

        }
    }

    private fun setBindingData() {
        binding.viewModel = viewModel // ViewModel 바인딩
    }


    // 해당 Fragment의 높이를 구하여 ClassHomeFragment의 ScrollView 높이로 지정
    private fun setClassHomeScrollViewHeight() {
        viewTreeObserver = binding.mainContainer.viewTreeObserver
        viewTreeObserver?.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                // ClassHomeFragment의 ViewPager ScrollView에 지정할 높이 구하기
                val sumHeight =
                    // 클래스 추가하기 버튼 높이
                    binding.classAddCardView.measuredHeight +
                            // 클래스 카운트 TextView의 높이
                            binding.classCountTextView.measuredHeight +
                            // 뷰 사이의 마진값
                            UnitConverter.convertDPtoPX(
                                requireContext(),
                                48 + 28 //(48은 뷰 사이의 마진값, 28은 화면이 잘려서 넣은 추가 높이값)
                            ) +
                            // 리사이클러뷰 개수만큼의 높이
                            UnitConverter.convertDPtoPX(
                                requireContext(),
                                116
                            ) * classAdapter?.itemCount!!

                ClassHomeViewModel.screenHeight.value = sumHeight

                // viewTreeObserver 제거
                try {
                    viewTreeObserver?.removeOnGlobalLayoutListener(this)
                }catch (e: IllegalStateException) {
                    if(BuildConfig.DEBUG) {
                        Timber.d("ViewTree를 한 번만 실행시키기 위해 제거했기 때문에 발생하는 예외")
                    }
                }
            }
        })
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