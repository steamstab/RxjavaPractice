package com.mintdev.rxjavapractice.ui

import android.app.Activity
import android.os.Bundle
import com.mintdev.rxjavapractice.R
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.a_simple_example.*

class ObservableSimpleActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_simple_example)

        mStartTv.setOnClickListener {
            observableSimpleExample()
        }
    }

    /**
     * 上游可以发送无限个onNext, 下游也可以接收无限个onNext.
     * 当上游发送了一个onComplete后, 上游onComplete之后的事件将会继续发送, 而下游收到onComplete事件之后将不再继续接收事件.
     * 当上游发送了一个onError后, 上游onError之后的事件将继续发送, 而下游收到onError事件之后将不再继续接收事件.
     * 上游可以不发送onComplete或onError.
     * 最为关键的是onComplete和onError必须唯一并且互斥, 即不能发多个onComplete, 也不能发多个onError,
     * 也不能先发一个onComplete, 然后再发一个onError, 反之亦然
     */
    private fun observableSimpleExample() {
        Observable.create(ObservableOnSubscribe<Int> {
            it.onNext(1)
            it.onNext(2)
            it.onNext(3)
            it.onNext(4)
            it.onComplete()
            it.onNext(5)
            it.onNext(6)
        }).subscribe(object : Observer<Int> {
            override fun onComplete() {
                mContentTv.text = "${mContentTv.text}\nComplete"
            }

            override fun onSubscribe(d: Disposable) {
                mContentTv.text = "${mContentTv.text}\nSubscribe"
            }

            override fun onNext(t: Int) {
                mContentTv.text = "${mContentTv.text}\nNext: $t"
            }

            override fun onError(e: Throwable) {
                mContentTv.text = "${mContentTv.text}\nError"
            }
        })
    }
}