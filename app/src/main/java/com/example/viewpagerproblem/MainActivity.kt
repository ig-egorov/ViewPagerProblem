package com.example.viewpagerproblem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    companion object {
        private const val ID = 12345678
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val frame = FrameLayout(this)
        frame.id = ID
        setContentView(
            frame, ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
        )

        if (savedInstanceState == null) {
            val mainFragment = MainFragment.newInstance() as Fragment
            val ft = supportFragmentManager.beginTransaction()
            ft.add(ID, mainFragment).commit()
        }
    }
}