package ru.s4nchez.androidlearning

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import ru.s4nchez.androidlearning.headless.HeadlessFragment
import ru.s4nchez.androidlearning.headless.HeadlessFragmentContract
import ru.s4nchez.androidlearning.headless.HeadlessFragmentListener

class ChildFragment : Fragment(), HeadlessFragmentListener {

    private val HEADLESS_TAG = "headless"
    private var headlessFragment: HeadlessFragment? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        headlessFragment = fragmentManager!!.findFragmentByTag(HEADLESS_TAG) as HeadlessFragment?
        if (headlessFragment == null) {
            headlessFragment = HeadlessFragment()
            fragmentManager!!.beginTransaction()
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