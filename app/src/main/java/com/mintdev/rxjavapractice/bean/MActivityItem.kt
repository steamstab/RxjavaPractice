package com.mintdev.rxjavapractice.bean

data class MActivityItem(
        var title: String = "",
        var desc: String = "",
        var to_class: Class<*>
)