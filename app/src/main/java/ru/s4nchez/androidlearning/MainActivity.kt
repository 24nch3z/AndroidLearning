package ru.s4nchez.androidlearning

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val HEADLESS_TAG = "headless"
    private var headlessFragment: HeadlessFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        headlessFragment = supportFragmentManager.findFragmentByTag(HEADLESS_TAG) as HeadlessFragment?
        if (headlessFragment == null) {
            headlessFragment = HeadlessFragment()
            supportFragmentManager.beginTransaction()
                    .add(headlessFragment!!, HEADLESS_TAG)
                    .commit()
        }

        action.setOnClickListener { headlessFragment?.let { (it as HeadlessFragmentContract).start(5) } }
    }
}
