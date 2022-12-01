package com.rostelekom.simplenotes.ui.screens.notes_editing

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rostelekom.simplenotes.R
import com.rostelekom.simplenotes.databinding.FragmentNotesEditingBinding

class NotesEditingFragment : Fragment() {


    lateinit var binding: FragmentNotesEditingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNotesEditingBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        println("ЯЯ не умею веселиться, давно пора признаться, как говориться - танцуй самааа")
    }

}