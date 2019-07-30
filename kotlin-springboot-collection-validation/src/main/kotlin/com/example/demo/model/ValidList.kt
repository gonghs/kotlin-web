package com.example.demo.model

import javax.validation.Valid

/**
 * list校验实体
 *
 * @author maple
 * @version 1.0
 * @since 2019-07-30 14:54
 */
class ValidList1<T> : MutableList<T> {
    override fun iterator(): MutableIterator<T> {
        return list.iterator()
    }

    override fun listIterator(): MutableListIterator<T> {
        return list.listIterator()
    }

    override fun listIterator(index: Int): MutableListIterator<T> {
        return list.listIterator(index)
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        return list.subList(fromIndex, toIndex)
    }

    override fun add(element: T): Boolean {
        return list.add(element)
    }

    override fun add(index: Int, element: T) {
        return list.add(index, element)
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        return list.addAll(index, elements)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        return list.addAll(elements)
    }

    override fun clear() {
        list.clear()
    }

    override fun remove(element: T): Boolean {
        return list.remove(element)
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        return list.removeAll(elements)
    }

    override fun removeAt(index: Int): T {
        return list.removeAt(index)
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        return list.retainAll(elements)
    }

    override fun set(index: Int, element: T): T {
        return list.set(index, element)
    }

    override val size: Int
        get() = list.size

    override fun contains(element: T): Boolean {
        return list.contains(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return list.containsAll(elements)
    }

    override fun get(index: Int): T {
        return list[index]
    }

    override fun indexOf(element: T): Int {
        return list.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return list.isEmpty()
    }

    override fun lastIndexOf(element: T): Int {
        return list.lastIndexOf(element)
    }

    @Valid
    val list: MutableList<T> = mutableListOf()
}

class ValidList<T> {
    @Valid
    val list: List<T>? = null
}

class ValidList2<T>(val list: MutableList<T>) : MutableList<T> by list {
    @Valid
    var mlist: MutableList<T> = list
    constructor() : this(mutableListOf())
}