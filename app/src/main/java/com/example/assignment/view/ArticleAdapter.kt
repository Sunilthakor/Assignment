package com.example.assignment.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R
import com.example.assignment.databinding.LytArticleBinding
import com.example.assignment.model.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lyt_article.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ArticleAdapter constructor(articleList: ArrayList<Article>, context: Context): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    lateinit var binding: LytArticleBinding
    var articleListData: ArrayList<Article> = ArrayList()

    init {
        this.articleListData.addAll(articleList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.lyt_article, parent, false)
        return ArticleViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return articleListData.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articleListData[position])
    }

    fun addMoreArticles(articleList: ArrayList<Article>) {
        this.articleListData.addAll(articleList)
        notifyDataSetChanged()
    }
   inner class ArticleViewHolder (var articleView: View): RecyclerView.ViewHolder(articleView) {
        fun bind(article: Article) {
            with(articleView) {
                //avatar.setImageResource(article.user[0].avatar)
                if (article.user.isNotEmpty()) {
                    Picasso.get().load(article.user[0].avatar).resize(50, 50).centerCrop().into(avatar)
                    tv_user_name.text = article.user[0].name + " " + article.user[0].lastName
                    tv_user_designation.text = article.user[0].designation
                }

                if (article.media.isNotEmpty()) {
                    iv_article_image.visibility = View.VISIBLE
                    tv_article_title.visibility = View.VISIBLE
                    tv_article_url.visibility = View.VISIBLE

                    Picasso.get().load(article.media[0].image).into(iv_article_image)
                    tv_article_title.text = article.media[0].title
                    tv_article_url.text = article.media[0].url
                } else {
                    iv_article_image.visibility = View.GONE
                    tv_article_title.visibility = View.GONE
                    tv_article_url.visibility = View.GONE
                }

                tv_article_content.text = article.content
                tv_article_likes.text = if (article.likes > 0) getLikesCount(article.likes) else "0"
                tv_article_comments.text = if (article.comments > 0) getCommentsCount(article.comments) else "0"
                tv_article_post_time.text = displayArticlePostedTime(convertStringToDate(article.createdAt), Date())
            }
        }

       private fun getLikesCount(likes: Double): String {
           var convertedLikes = ""
           if (likes < 1000.0) {
               convertedLikes = (likes.toInt()).toString() + " Likes"
           } else if (likes > 1000.0) {
               val number3digits:Double = Math.round((likes / 1000.0) * 1000.0) / 1000.0
               val number2digits:Double = Math.round(number3digits * 100.0) / 100.0
               val solution:Double = Math.round(number2digits * 10.0) / 10.0
               convertedLikes = solution.toString() +"K Likes"
           }
           return convertedLikes
       }

       private fun getCommentsCount(comment: Double): String {
           var convertedComments = ""

           if (comment < 1000) {
               convertedComments = (comment.toInt()).toString() + " Comments"
           } else {
               val number3digits: Double = Math.round((comment / 1000.0) * 1000.0) / 1000.0
               val number2digits: Double = Math.round(number3digits * 100.0) / 100.0
               val solution: Double = Math.round(number2digits * 10.0) / 10.0
               convertedComments = solution.toInt().toString() + "K Comments"
           }
           return convertedComments
       }

       private fun displayArticlePostedTime(postedDate: Date, currentDate: Date): String {
           var postedTime = ""
           var difference = currentDate.time - postedDate.time
           val secondsInMilli: Long = 1000
           val minutesInMilli = secondsInMilli * 60
           val hoursInMilli = minutesInMilli * 60
           val daysInMilli = hoursInMilli * 24

           val elapsedDays: Long = difference / daysInMilli
           difference %= daysInMilli

           val elapsedHours: Long = difference / hoursInMilli

           if (elapsedDays > 1) {
               postedTime = "$elapsedDays Days"

               if (elapsedHours > 0) {
                   postedTime += " $elapsedHours Hours"
               }
           } else {
               postedTime = "$elapsedHours hours"
           }

           return postedTime
       }

       private fun convertStringToDate(date: String): Date {
           val receivedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
           val postedDate: Date? = receivedDate.parse(date)
           //val currentDate = Date()
           Log.d("received date: ", postedDate.toString())
           //Log.d("current date: ", currentDate.toString())
           return postedDate!!
       }
    }
}