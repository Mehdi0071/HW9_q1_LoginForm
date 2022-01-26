package com.example.q1_loginform

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.q1_loginform.databinding.ActivityFragmentFirstBinding
import com.google.android.material.button.MaterialButton

class FirstFragment : Fragment(R.layout.activity_fragment_first) {

    interface OnDataStore {
        fun dataStore(id : List<Int>)
    }

    var onDataStore : OnDataStore? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = mutableListOf<Int>()
        id.apply {
            add(R.id.et_fullname)
            add(R.id.et_username)
            add(R.id.et_email)
            add(R.id.et_password)
        }

        view.findViewById<MaterialButton>(R.id.btn_register).setOnClickListener {
            onDataStore?.dataStore(id)
        }

    }

}