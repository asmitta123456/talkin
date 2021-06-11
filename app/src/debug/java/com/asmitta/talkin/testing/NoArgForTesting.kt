package com.asmitta.talkin.testing

@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class NoArgClass

@NoArgClass
@Target(AnnotationTarget.CLASS)
annotation class NoArgForTesting