package ru.s4nchez.androidlearning

interface HeadlessFragmentContract {
    fun load()
    fun setListener(listener: HeadlessFragmentListener)
}