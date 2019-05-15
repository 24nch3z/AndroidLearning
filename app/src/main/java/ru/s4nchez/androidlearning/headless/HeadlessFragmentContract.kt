package ru.s4nchez.androidlearning.headless

interface HeadlessFragmentContract {
    fun load()
    fun setListener(listener: HeadlessFragmentListener)
}