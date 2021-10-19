package com.onurbarman.inviousgchallenge.ui.custom

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.onurbarman.inviousgchallenge.databinding.DialogLoadingBinding

class LoadingDialog : DialogFragment() {
    private var binding: DialogLoadingBinding? = null
    private lateinit var _dialog: Dialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.run {
            binding = DialogLoadingBinding.inflate(layoutInflater).apply {
                animationLottie.playAnimation()
            }
            _dialog = AlertDialog.Builder(this).apply {
                binding?.let {
                    setView(it.root)
                }
            }.create()
            _dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            _dialog.setCancelable(false)
        } ?: throw IllegalStateException("Activity cannot be null")
        return _dialog
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding?.animationLottie?.cancelAnimation()
        binding = null
    }
}