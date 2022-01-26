package com.example.q1_loginform

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.q1_loginform.databinding.ActivityFragmentSecondBinding
import com.google.android.material.button.MaterialButton



class SecondFragment : Fragment(R.layout.activity_fragment_second) {

    interface OnSaveListener {
        fun onSave()
    }

    var onSaveListener : OnSaveListener? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = requireArguments().get("userinformation") as List<String>

        view.findViewById<TextView>(R.id.tv_fullname).text = list[0]
        view.findViewById<TextView>(R.id.tv_username).text = list[1]
        view.findViewById<TextView>(R.id.tv_email).text = list[2]
        view.findViewById<TextView>(R.id.tv_password).text = list[3]



        view.findViewById<MaterialButton>(R.id.btn_save).setOnClickListener {
            onSaveListener?.onSave()
        }

    }

}