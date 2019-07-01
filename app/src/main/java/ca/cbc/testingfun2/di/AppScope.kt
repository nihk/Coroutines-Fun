package ca.cbc.testingfun2.di

import javax.inject.Scope

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION
)
@Retention(AnnotationRetention.RUNTIME)
@Scope
annotation class AppScope