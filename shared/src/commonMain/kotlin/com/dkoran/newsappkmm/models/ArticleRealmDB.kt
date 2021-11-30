package com.dkoran.newsappkmm.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


class ArticleRealmDB : RealmObject {
      @PrimaryKey
      var id:Int? = null

    var author: String?  = null

    var content: String? = null

    var description: String? = null

    var publishedAt: String? = null

    var title: String? = null

    var url: String? = null

    var urlToImage: String? = null



 }
