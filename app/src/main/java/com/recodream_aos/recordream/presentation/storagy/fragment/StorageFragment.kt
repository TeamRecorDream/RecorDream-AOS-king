package com.recodream_aos.recordream.presentation.storagy.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.recodream_aos.recordream.R
import com.recodream_aos.recordream.databinding.FragmentStorageBinding
import com.recodream_aos.recordream.presentation.document.DocumentActivity
import com.recodream_aos.recordream.presentation.storagy.MyDecoration
import com.recodream_aos.recordream.presentation.storagy.StorageViewModel
import com.recodream_aos.recordream.presentation.storagy.adapter.StorageEmotionAdapter
import com.recodream_aos.recordream.presentation.storagy.adapter.StorageGridAdapter
import com.recodream_aos.recordream.presentation.storagy.adapter.StorageListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StorageFragment : Fragment() {
    private var _binding: FragmentStorageBinding? = null
    private val binding get() = _binding ?: error("binding not init")
    private var storageCheck = true
    private var emotionCheck = 0
    private lateinit var storageGridAdapter: StorageGridAdapter
    private lateinit var storageListAdapter: StorageListAdapter
    private val storageViewModel by viewModels<StorageViewModel>()
    private val storageEmotionAdapter = StorageEmotionAdapter(::emotionClick)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStorageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storageViewModel.initServer(0)
        emotionAdapterInit()
        initGridAdapter()
        binding.viewModel = storageViewModel
        observer()
        selectShowView()
    }

    private fun observer() {
        with(storageViewModel) {
            storageCheckList.observe(viewLifecycleOwner) {
                if (it) {
                    storageCheck = true
                    initGridAdapter()
                    storageGridAdapter.submitList(storageViewModel.storageRecords.value)
                } else {
                    storageCheck = false
                    initListAdapter()
                    storageViewModel.initServer(emotionCheck)
                    storageListAdapter.submitList(storageViewModel.storageRecords.value)

                }
            }

            storageRecords.observe(viewLifecycleOwner) {
                binding.tvStorageNoList.visibility = View.INVISIBLE
                if (storageCheck) {
                    storageGridAdapter.submitList(it)
                    storageEmotionAdapter.submitList(storageViewModel.storageList)
                } else {
                    storageEmotionAdapter.submitList(storageViewModel.storageList)
                    storageListAdapter.submitList(it)
                }
            }
            storageRecordCount.observe(viewLifecycleOwner) {
                val recordAllCount = getString(R.string.store_records_count, it)
                binding.tvStorageRecordCount.text = recordAllCount
                if (it == 0) {
                    binding.tvStorageNoList.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun emotionAdapterInit() {
        binding.rvStorageEmotion.adapter = storageEmotionAdapter
        storageEmotionAdapter.submitList(storageViewModel.storageList)
    }

    private fun initGridAdapter() {
        storageGridAdapter =
            StorageGridAdapter {
                val intent = Intent(requireContext(), DocumentActivity::class.java)
            }
        binding.rvStorage.adapter = storageGridAdapter
        binding.rvStorage.layoutManager = GridLayoutManager(context, 2)
    }

    private fun initListAdapter() {
        storageListAdapter =
            StorageListAdapter {
                val intent = Intent(requireContext(), DocumentActivity::class.java)
            }
        binding.rvStorage.adapter = storageListAdapter
        binding.rvStorage.layoutManager = LinearLayoutManager(context)
    }

    private fun selectShowView() {
        with(binding) {
            ivStorageSelectGallery.isSelected = true
            ivStorageSelectGallery.setOnClickListener {
                storageViewModel.isCheckShow(true)
                storageCheck = true
                ivStorageSelectGallery.isSelected = true
                ivStorageSelectList.isSelected = false
            }
            ivStorageSelectList.setOnClickListener {
                storageViewModel.isCheckShow(false)
                initListAdapter()
                ivStorageSelectGallery.isSelected = false
                ivStorageSelectList.isSelected = true
            }
        }
    }

    private fun emotionClick(index: Int) {
        storageViewModel.initServer(index)
        emotionCheck = index
        for (i in 0..6) {
            storageViewModel.storageList[i].isSelected = false
        }
        storageViewModel.storageList[index].isSelected = true
        Log.d("storageemotion", "emotionClick: ${storageViewModel.storageList}")
//        storageEmotionAdapter.submitList(storageViewModel.storageList)
        storageEmotionAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
