package com.example.dimoraapp.data

import com.example.dimoraapp.R
import com.example.dimoraapp.model.Picture

class Datasource {
    fun loadPictures(): List<Picture> {
        return listOf(
            Picture(R.drawable.image1, R.string.Heading1 , R.string.price1),
            Picture(R.drawable.image2, R.string.Heading2 , R.string.price2),
            Picture(R.drawable.image3, R.string.Heading3 , R.string.price3),
            Picture(R.drawable.image4, R.string.Heading4 , R.string.price4)
        )
    }
}