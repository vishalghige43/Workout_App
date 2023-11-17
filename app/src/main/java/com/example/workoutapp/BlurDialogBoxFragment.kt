package com.example.workoutapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import fr.tvbarthel.lib.blurdialogfragment.BlurDialogFragment
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment

class BlurDialogBoxFragment : SupportBlurDialogFragment() {
    private var btnYes:Button?=null;
    private var btnNo:Button?=null;
    interface DialogListener {
        fun onPositiveButtonClick()

        fun onNegativeButtonClick()
    }
    var dialogListener: DialogListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        isCancelable = false;
        // Inflate the layout for this fragment
        var root:View= inflater.inflate(R.layout.fragment_blu_dailog_box, container, false);
        btnYes=root.findViewById(R.id.btnYes);
        btnNo=root.findViewById(R.id.btnNo);
        return root;
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnYes?.setOnClickListener {
            dialogListener?.onPositiveButtonClick()
            dismiss()
        }

        // Handle negative button click
        btnNo?.setOnClickListener {
            dialogListener?.onNegativeButtonClick()
            dismiss()
        }
    }
}

