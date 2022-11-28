package com.rostelekom.simplenotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.rostelekom.simplenotes.databinding.ActivityMainBinding
import com.rostelekom.simplenotes.ui.adapters.NotesAdapter
import com.rostelekom.simplenotes.ui.models.Notes

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply { lifecycleOwner = this@MainActivity }
        supportActionBar!!.title = "Notes"

        val notesRv = binding.mainNotesRv
        val rvAdapter = NotesAdapter()
        notesRv.apply {
            adapter = rvAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
        }

        val testNotesList: ArrayList<Notes> = arrayListOf(
            Notes("Любимое занятие", "Порой я очень люблю анально ублажать себя, и это всё что стоит знать"),
            Notes("Люблю себя", "Лорем ипсум дорор сит амет, аверс катаверс")
        )
        rvAdapter.changeList(testNotesList)

    }

}