package com.oka.room.jooka.com

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.DialogFragment

class InternetChecker56ji5 : DialogFragment(R.layout.dialog_internet_checker_56ji5) {

    private lateinit var button56ji5: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isCancelable = false
        super.onViewCreated(view, savedInstanceState)
        button56ji5 = view.findViewById(R.id.b_check_int_56ji5)
        button56ji5.setOnClickListener {
            if (view.context.checkForConnection56ji5()) {
                dismiss()
                isDismissed56ji5 = true
            }
        }
    }
}