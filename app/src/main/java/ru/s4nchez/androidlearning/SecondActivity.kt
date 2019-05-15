package ru.s4nchez.androidlearning

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity(), HeadlessFragmentListener {

    private val HEADLESS_TAG = "headless"
    private var headlessFragment: HeadlessFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        headlessFragment = supportFragmentManager.findFragmentByTag(HEADLESS_TAG) as HeadlessFragment?
        if (headlessFragment == null) {
            headlessFragment = HeadlessFragment()
            supportFragmentManager.beginTransaction()
                    .add(headlessFragment!!, HEADLESS_TAG)
                    .commit()
        }

        (headlessFragment as HeadlessFragmentContract?)?.let {
            it.setListener(this)
            it.load()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        headlessFragment = null
    }

    override fun onLoad(result: Double) {
        Logger.l("Call in SecondActivity")
        action.text = result.toString()
    }
}