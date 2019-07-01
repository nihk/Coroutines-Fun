package ca.cbc.testingfun2.ui

import android.os.Bundle
import ca.cbc.testingfun2.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


/**
fun main() = runBlocking {
Retrofit.Builder()
.baseUrl("https://jobs.github.com/")
.addConverterFactory(MoshiConverterFactory.create())
.build()
.create(GitHubJobsService::class.java)
.fetchJobs()
.forEach(::println)

println("Nice")
}

        */