package com.fh.unsplash.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.fh.unsplash.databinding.FragmentFilterDialogBinding
import com.fh.unsplash.preferences.MainPreferences
import com.fh.unsplash.viewmodel.LatestPhotoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject



@AndroidEntryPoint
class FilterDialog : CustomDialog() {
    private lateinit var binding: FragmentFilterDialogBinding



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFilterDialogBinding.inflate(inflater, container, false)

        binding.popular.setOnClickListener {

        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        filterPhotos()

    }

    private fun filterPhotos(){
        lifecycleScope.launchWhenStarted {
            binding.apply {
                popular.setOnClickListener {
                    savePreferences("popular")
                    dialog?.dismiss()
                }

                latest.setOnClickListener {
                    savePreferences("latest")
                    dialog?.dismiss()
                }
                oldest.setOnClickListener {
                    savePreferences("oldest")
                    dialog?.dismiss()
                }

            }
        }
    }

    private fun savePreferences(value:String){

    }

}