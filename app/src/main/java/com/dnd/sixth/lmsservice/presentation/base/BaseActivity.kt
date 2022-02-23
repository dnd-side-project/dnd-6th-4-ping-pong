package com.dnd.sixth.lmsservice.presentation.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity<T: ViewDataBinding, S: BaseViewModel> : AppCompatActivity() {
    
    private var viewDataBinding: T? = null

    abstract val layoutResId: Int
    abstract val viewModel: S
    abstract fun initActivity()
    val binding: T by lazy {
        getViewDataBinding()!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Override될 layoutResId로 data binding 객체 생성
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResId)
        // Live data를 사용하기 위한 lifecycleOwner 지정
        viewDataBinding?.lifecycleOwner = this@BaseActivity
        // viewModel의 화면 전환 MutableLiveData 감지
        viewModel.activityToStart.observe(this, {
            startActivity(Intent(this, it.first.java))
        })

        // 기본적으로 onCreate에서 initActivity() 메서드를 실행하기 때문에
        // BaseActivity를 상속받는 액티비티에서는 initActivity만 구현하면 된다.
        initActivity()
    }


    override fun onDestroy() {
        super.onDestroy()
        viewDataBinding = null
    }

    // binding 객체를 가져오는 메소드
    private fun getViewDataBinding(): T? = viewDataBinding

    // 토스트 띄우기
    fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    // 스낵바 띄우기
    fun showSnackBar(msg: String) {
        Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
    }


}