package com.recodream_aos.recordream.presentation.document

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.recodream_aos.recordream.R
import com.recodream_aos.recordream.databinding.ActivityDocumentBinding
import com.recodream_aos.recordream.util.customview.CustomDialog

class DocumentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDocumentBinding

    // 더보기 바텀시트, 삭제 확인창 변수
    private val documentBottomSheetFragment = DocumentBottomSheetFragment()
    private lateinit var dialog: CustomDialog

    //    나중에 뷰모델 써서 불러올 예정
    private val viewModel by viewModels<DocumentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDocumentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recordId = intent.getStringExtra(RECORD_ID) ?: error("need record Id")

        initAdapter()
        initTabLayout()
        initCloseButton()
        initNetwork(recordId)
        observeData()

        binding.ivDotsMore.setOnClickListener { createBottom() }
    }

    private fun createBottom() {
        documentBottomSheetFragment.show(supportFragmentManager, documentBottomSheetFragment.tag)
    }

    private fun initAdapter() {
        val documentViewPagerAdapter = DocumentViewPagerAdapter(this)
        binding.vpDocument.adapter = documentViewPagerAdapter
    }

    // 탭 이름 적용 없이 인디케이터만 움직이게 수정 예정
    private fun initTabLayout() {
        val tabLabel = listOf("나의 꿈 기록", "노트")

        TabLayoutMediator(binding.tlDocument, binding.vpDocument) { tab, position ->
            tab.text = tabLabel[position]
        }.attach()
    }

    private fun initCloseButton() {
        binding.ivDocumentClose.setOnClickListener {
            finish()
        }
    }

    //
    private fun initNetwork(id: String) {
        viewModel.getData(id)
    }

    private fun applyData(response: ResponseDocumentDreamRecord) {
    }

    private fun observeData() {
        viewModel.documentResponse.observe(this) {
            applyData(it)
        }
    }

    companion object {
        private const val RECORD_ID = "RECORD_ID"

        fun getIntent(context: Context, id: String?): Intent =
            Intent(context, DocumentActivity::class.java).apply {
                putExtra(RECORD_ID, id)
            }
    }

    fun checkEmotionIcon(color: Int) = when (color) {
        1 -> R.drawable.feeling_l_joy
        2 -> R.drawable.feeling_l_sad
        3 -> R.drawable.feeling_l_scary
        4 -> R.drawable.feeling_l_strange
        5 -> R.drawable.feeling_l_shy
        else -> R.drawable.feeling_l_blank
    }

    fun checkEmotionBackground(color: Int) = when (color) {
        1 -> R.drawable.card_m_yellow
        2 -> R.drawable.card_m_blue
        3 -> R.drawable.card_m_red
        4 -> R.drawable.card_m_purple
        5 -> R.drawable.card_m_pink
        else -> R.drawable.card_m_white
    }
}
