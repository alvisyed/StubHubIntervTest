package com.dynamicdev.stubhubinterview.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dynamicdev.stubhubinterview.databinding.PhotoFragmentLayoutBinding
import com.dynamicdev.stubhubinterview.model.PhotoApi
import com.dynamicdev.stubhubinterview.model.PresentationData
import com.dynamicdev.stubhubinterview.repository.PhotoRepository
import com.dynamicdev.stubhubinterview.repository.ViewState
import com.dynamicdev.stubhubinterview.showAToast
import com.dynamicdev.stubhubinterview.viewmodel.PhotoViewModel
import org.koin.java.KoinJavaComponent.inject

private const val TAG = "PhotoFragment"
class PhotoFragment : Fragment() {

//    private val photoApi: PhotoApi by lazy {
//        PhotoApi.initRetrofit().create(PhotoApi::class.java)
//    }
//    private val repository: PhotoRepository by lazy {
//        PhotoRepository(photoApi)
//    }
//    private val viewModel: PhotoViewModel by lazy {
//        PhotoViewModel.PhotoViewModelProvider(
//            repository
//        ).create(PhotoViewModel::class.java)
//    }
    val viewModelProvider:
        PhotoViewModel.PhotoViewModelProvider by inject(clazz = PhotoViewModel.PhotoViewModelProvider::class.java)

    private val adapter: PhotoAdapter by lazy {
        PhotoAdapter()
    }
    private lateinit var binding: PhotoFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = PhotoFragmentLayoutBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews()
        val viewModel = viewModelProvider.create(PhotoViewModel::class.java)

        viewModel.photoLiveData.observe(viewLifecycleOwner){
            Log.d(TAG, "onCreateView: ")
            processViewState(it)
        }
        return view
    }

    private fun initViews() {
        binding.photoRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.photoRecyclerView.adapter = adapter
    }

    private fun processViewState(viewState: ViewState) {
        when (viewState){
            is ViewState.ViewData -> {udpateAdapter(viewState.viewData)}
            is ViewState.ViewError -> {showError(viewState.error)}
            is ViewState.ViewLoading -> {dismissLoading()}
        }
    }

    private fun dismissLoading() {
        Log.d(TAG, "dismissLoading: ")
        binding.progressBar.visibility = View.GONE
    }

    private fun showError(error: String) {
        Log.d(TAG, "showError: ")
        activity?.showAToast(error)
    }

    private fun udpateAdapter(viewData: List<PresentationData>) {
        Log.d(TAG, "udpateAdapter: $viewData")
        dismissLoading()
        adapter.setDataSet(viewData)
    }
}