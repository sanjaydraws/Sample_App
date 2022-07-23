package com.sanjayprajapat.sampleapp.utils



fun isPrimeNumber(number: Int?): String {

    if (number == null)
        return "Not Prime"
    for (i in 2..number / 2) {
        if (number % i == 0) {
            return "Not Prime"
        }
    }
    return "Prime"
}