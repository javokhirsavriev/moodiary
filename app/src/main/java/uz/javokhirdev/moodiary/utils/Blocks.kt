package uz.javokhirdev.moodiary.utils

typealias EmptyBlock = () -> Unit
typealias SingleBlock <T> = T.() -> Unit
typealias DoubleBlock <T, E> = (T, E) -> Unit
