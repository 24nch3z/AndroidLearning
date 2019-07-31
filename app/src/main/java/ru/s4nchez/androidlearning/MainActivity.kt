package ru.s4nchez.androidlearning

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("CheckResult")
class MainActivity : AppCompatActivity() {

//    private val phoneAdapter = PhoneAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recycler_view.adapter = phoneAdapter
//
//        DI.dataBase(this).phoneDao().getAll()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    phoneAdapter.setItems(it)
//                }, {
//                    Log.d("sssss", it.message)
//                })
//
//        clear_button.setOnClickListener {
//            Completable.fromCallable { DI.dataBase(this).phoneDao().dropTable() }
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe()
//        }
//
//        add_button.setOnClickListener {
//            if (input.text.toString().trim().isEmpty()) {
//                Toast.makeText(this, "Нужно ввести номер", Toast.LENGTH_SHORT).show()
//            } else {
//                val text = input.text.toString().trim()
//                input.clear()
//                Completable.fromCallable {
//                    DI.dataBase(this).phoneDao()
//                            .insert(Phone(number = text))
//                }
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe()
//            }
//        }


        // ============================
        // ============================

        DI.dataBase(this).userDao().getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("", "")
                }, {
                    Log.d("sssss", it.message)
                })

        clear_button.setOnClickListener {
            val str = input.text.toString()

            val id: Long
            try {
                id = str.toLong()
                DI.dataBase(this).userDao().deleteByID(id)
                        .subscribeOn(Schedulers.io())
                        .subscribe()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Нужно ввести номер", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun EditText.clear() {
        setText("")
    }
}
