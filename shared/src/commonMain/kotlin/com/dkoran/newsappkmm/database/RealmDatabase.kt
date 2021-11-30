package com.dkoran.newsappkmm.database

import com.dkoran.newsappkmm.models.ArticleRealmDB
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.delete
import io.realm.objects

class RealmDatabase {
    val realm : Realm by lazy {
        val configuration = RealmConfiguration.with(schema = setOf(ArticleRealmDB::class))
        Realm.open(configuration)
    }
    fun getAllArticles(): List<ArticleRealmDB> {
        return realm.objects(ArticleRealmDB::class)
    }

    fun addArticle(article: ArticleRealmDB) {
        realm.writeBlocking {
            copyToRealm(article)
        }
    }

    fun deleteArticle(title: String) {
        realm.writeBlocking {
            objects<ArticleRealmDB>().query("title = $0", title)
                .first()
                .let { findLatest(it) }
                ?.delete()
                ?: throw IllegalStateException("Book not found.")
        }
    }

    fun clearAllArticle() {
        realm.writeBlocking {
            objects<ArticleRealmDB>().delete()
        }
    }
}