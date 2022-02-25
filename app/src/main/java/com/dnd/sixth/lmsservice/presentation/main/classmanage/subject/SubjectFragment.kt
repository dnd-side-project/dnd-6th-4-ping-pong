package com.dnd.sixth.lmsservice.presentation.main.classmanage.subject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.dnd.sixth.lmsservice.BuildConfig
import com.dnd.sixth.lmsservice.R
import com.dnd.sixth.lmsservice.databinding.FragmentClassBinding
import com.dnd.sixth.lmsservice.databinding.LayoutEditDeleteBottomSheetBinding
import com.dnd.sixth.lmsservice.domain.entity.SubjectEntity
import com.dnd.sixth.lmsservice.presentation.adapter.recyclerAdapter.SubjectAdapter
import com.dnd.sixth.lmsservice.presentation.base.BaseFragment
import com.dnd.sixth.lmsservice.presentation.feedback.check.CheckFeedBackActivity
import com.dnd.sixth.lmsservice.presentation.lesson.progress.ClassProgressActivity
import com.dnd.sixth.lmsservice.presentation.listner.OnRecyclerItemClickListener
import com.dnd.sixth.lmsservice.presentation.main.classmanage.ClassManageViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.calendar.CalendarViewModel
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.create.SubjectCreateActivity
import com.dnd.sixth.lmsservice.presentation.main.classmanage.subject.edit.SubjectEditActivity
import com.dnd.sixth.lmsservice.presentation.utility.UnitConverter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.layout_edit_delete_bottom_sheet.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class SubjectFragment : BaseFragment<FragmentClassBinding, SubjectViewModel>(),
    View.OnClickListener, OnRecyclerItemClickListener {
    override val layoutResId: Int
        get() = R.layout.fragment_class

    override val viewModel: SubjectViewModel by viewModel()
    private val hostViewModel: ClassManageViewModel by sharedViewModel()

    private var subjectAdapter: SubjectAdapter? = null
    private var viewTreeObserver: ViewTreeObserver? = null
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>


    companion object {
        const val INTENT_CREATE_SUBJECT_ENTITY_KEY = "createSubject"
        const val INTENT_UPDATE_SUBJECT_ENTITY_KEY = "updateSubject"
        const val INTENT_SUBJECT_ID_KEY = "subjectId"
        const val INTENT_TEACHER_NAME_KEY = "teacherName"

        const val INTENT_CREATE_SUBJECT_ACTIVITY_CODE = 1001
        const val INTENT_UPDATE_SUBJECT_ACTIVITY_CODE = 1002
    }


    // 최상위 ViewTreeObserver (높이를 구하기 위한 변수)
    // var viewTreeObserver: ViewTreeObserver? = null

    // 액티비티 초기화 메서드
    override fun initActivity() {
        setBindingData() // 필요한 데이터 바인딩
        initView() // 뷰 초기화
    }

    private fun initView() {
        with(binding) {
            makeClassBtn.setOnClickListener(this@SubjectFragment)
            classAddBtn.setOnClickListener(this@SubjectFragment)

            subjectAdapter = SubjectAdapter(
                hostViewModel?.generalSubjectDataList?.value!!,
                this@SubjectFragment
            ) // 수업 리사이클러뷰 어댑터
            with(classRecyclerView) {
                adapter = subjectAdapter // 어댑터 적용
                layoutManager = LinearLayoutManager(requireContext())
            }


            // 수업 리스트가 변경됨에 따라 화면 크기 조절을 하기 위한 Observer
            hostViewModel?.generalSubjectDataList?.observe(this@SubjectFragment) {
                // ClassManageFragment(ParentFragment)에 수업 개수 전달.
                ClassManageViewModel.classCount.value = it.size

                if (hostViewModel?.hasClass() == true) { // 수업이 있다면
                    //setClassHomeScrollViewHeight() // 수업 RecyclerView Item 사이즈에 맞게 HomeFragment의 Scroll 높이 재설정
                    noClassContainer.visibility = View.GONE // '수업이 없어요' 화면 가리기
                    classRecyclerView.visibility = View.VISIBLE // '수업 목록' 리사이클러뷰 보여주기
                    classAddCardView.visibility = View.VISIBLE // '(수업이 있을 때 보일) 수업 추가 버튼' 보여주기
                } else { // 수업이 없다면
                    noClassContainer.visibility = View.VISIBLE // '수업이 없어요' 화면 보여주기
                    classRecyclerView.visibility = View.GONE // '수업 목록' 리사이클러뷰 가리기
                    classAddCardView.visibility = View.GONE // '(수업이 있을 때 보일)수업 추가 버튼' 가리기
                }

                // 리사이클러뷰 갱신
                subjectAdapter?.updateItem(it)

                // 리사이클러뷰의 개수가 추가되면 화면의 높이를 다시 측정한다.
                setClassHomeScrollViewHeight()
            }

            setActivityLauncher()


        }

    }

    private fun setBindingData() {
        binding.viewModel = viewModel
        binding.hostViewModel = hostViewModel // ViewModel 바인딩
    }

    // 액티비티 런처 설정
    private fun setActivityLauncher() {
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == INTENT_CREATE_SUBJECT_ACTIVITY_CODE) {
                    // 액티비티 실행 결과
                    val resultIntent = result.data

                    // 수업을 생성하고 새롭게 반환된 Subject Entity
                    val newSubjectEntity =
                        resultIntent?.getSerializableExtra(INTENT_CREATE_SUBJECT_ENTITY_KEY) as SubjectEntity

                    newSubjectEntity.also {
                        showInviteDialog(it) // 초대 코드 다이얼로그를 보여준다.
                        hostViewModel!!.addSubject(it) // 수업 리스트에 추가한다.
                    }

                }
            }
    }


    // 해당 Fragment의 높이를 구하여 ClassHomeFragment의 ScrollView 높이로 지정
    private fun setClassHomeScrollViewHeight() {
        viewTreeObserver = binding.mainContainer.viewTreeObserver
        viewTreeObserver?.addOnGlobalLayoutListener(viewTreeObserverCallback)
        viewTreeObserverCallback.onGlobalLayout()
    }

    override fun onResume() {
        super.onResume()
        /* 캘린더가 펼쳐지면 Observer를 통해 높이를 다시 측정하기 때문에
        *  캘린더를 먼저 펼친 후에 SubjectFragment의 높이를 재측정한다.
        *  */

        // 클래스 Fragment가 재게되면, 캘린더 Fragment의 Calendar를 다시 펼친다.
        if (CalendarViewModel.isExpanded.value == false) {
            CalendarViewModel.expandCalendar()
        }
        // Host Fragment의 ScrollView 높이 재설정
        setClassHomeScrollViewHeight()
    }

    // 초대코드 다이얼로그를 보여준다.
    private fun showInviteDialog(subjectEntity: SubjectEntity) {
        val inviteDialog = InviteDialogFragment().apply {
            val bundle = Bundle().apply {
                putInt(INTENT_SUBJECT_ID_KEY, subjectEntity.id?.toInt()!!)
                putString(INTENT_TEACHER_NAME_KEY, hostViewModel.getName()!!)
            }
            arguments = bundle
        }

        inviteDialog.show(childFragmentManager, null)
    }

    private val viewTreeObserverCallback = object :
        ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {

            // ClassHomeFragment의 ViewPager ScrollView에 지정할 높이 구하기
            val sumHeight =
                // 클래스 추가하기 버튼 높이
                binding.classAddCardView.measuredHeight +
                        /*// 클래스 카운트 TextView의 높이
                        binding.classCountTextView.measuredHeight +*/
                        // 뷰 사이의 마진값
                        UnitConverter.convertDPtoPX(
                            requireContext(),
                            48 + 28 //(48은 뷰 사이의 마진값, 28은 화면이 잘려서 넣은 추가 높이값)
                        ) +
                        // 리사이클러뷰 개수만큼의 높이
                        UnitConverter.convertDPtoPX(
                            requireContext(),
                            116
                        ) * subjectAdapter?.itemCount!!

            ClassManageViewModel.screenHeight.value = sumHeight
            Timber.tag("SubjectFragment Height").d("$sumHeight")

            try {
                // viewTreeObserver 제거
                if ((binding.classAddCardView.visibility == View.VISIBLE && binding.classAddCardView.measuredHeight > 0) ||
                    binding.classAddCardView.visibility == View.GONE
                ) {
                    viewTreeObserver?.removeOnGlobalLayoutListener(this)
                }
            } catch (e: IllegalStateException) {
                if (BuildConfig.DEBUG) {
                    Timber.d("ViewTree를 한 번만 실행시키기 위해 제거했기 때문에 발생하는 예외")
                }
                return
            }
        }
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.make_class_btn, R.id.class_add_btn -> {
                activityResultLauncher.launch(
                    Intent(
                        requireContext(),
                        SubjectCreateActivity::class.java
                    )
                )
            }
        }
    }

    override fun onClick(resId: Int, position: Int) {
        when (resId) {
            R.id.more_btn -> {
                val moreDialog = BottomSheetDialog(requireContext()).also { dialog ->
                    val moreBottomSheetBinding =
                        LayoutEditDeleteBottomSheetBinding.inflate(layoutInflater)
                    with(moreBottomSheetBinding) {
                        // 삭제 버튼 클릭시, 하단 Dialog 종료와 함께, 삭제 여부를 묻는 Dialog를 보여준다.
                        deleteBtn.setOnClickListener {
                            dialog.dismiss() // 하단 Dialog 종료
                            showDeleteClassDialog(position) // 삭제 여부를 묻는 Dialog show
                        }
                        // 수정 버튼 클릭시, 수업 정보를 Edit할 수 있는 Activity로 이동
                        editBtn.setOnClickListener {
                            // 수업 수정 Activity로 이동, ClassItem 전달
                            startActivity(
                                Intent(
                                    requireContext(),
                                    SubjectEditActivity::class.java
                                ).putExtra("classModel", hostViewModel.getClassModel(position))
                            )
                            dialog.dismiss() // 하단 Dialog 종료
                        }
                    }
                    dialog.setContentView(moreBottomSheetBinding.root)

                }

                moreDialog.show()
            }
            R.id.class_btn -> {
                // 선택한 사용자의 피드백 화면으로 이동합니다.
                startActivity(Intent(requireContext(), ClassProgressActivity::class.java).putExtra(
                    INTENT_SUBJECT_ID_KEY, hostViewModel.getClassModel(position).subjectId
                ))
            }
        }
    }


    // 클래스 삭제 여부를 묻는 다이얼로그를 보여준다.
    private fun showDeleteClassDialog(position: Int) {
        // 삭제를 묻는 Dialog Builder 생성
        val builder = AlertDialog.Builder(requireContext()).setMessage("클래스를 삭제하시겠어요?")
            .setPositiveButton(
                "삭제"
            ) { _, _ ->
                // 수업 삭제 로직 수행
                CoroutineScope(Dispatchers.IO).launch {
                    val isSuccess = hostViewModel.deleteSubject(position) // 수업 삭제
                    launch(Dispatchers.Main) {
                        if (isSuccess) {
                            // List와 RecyclerView에서도 삭제한 수업 데이터를 제거합니다.
                            hostViewModel.removeSubject(position)
                            subjectAdapter?.notifyItemRemoved(position)

                            // 성공 SnackBar 출력
                            showSnackBar(getString(R.string.success_delete_subject))
                        } else {
                            showSnackBar(getString(R.string.failed_delete_subject))
                        }
                    }
                }
            }.setNegativeButton("취소")
            { dialog, _ ->
                dialog.dismiss()
            }

        //
        builder.create().show()

    }

}