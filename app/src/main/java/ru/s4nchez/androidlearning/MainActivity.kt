package ru.s4nchez.androidlearning

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            replace(Fragment1())
        }
        button_show.setOnClickListener { show() }
        button_hide.setOnClickListener { hide() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun replace(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment, fragment::class.java.name)
                .commit()
        supportFragmentManager.executePendingTransactions()
    }

    private fun hide() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        fragment?.let {
            supportFragmentManager.beginTransaction()
                    .hide(it)
                    .commit()
        }
    }

    private fun show() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        fragment?.let {
            supportFragmentManager.beginTransaction()
                    .show(it)
                    .commit()
        }
    }
}
