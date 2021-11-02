package com.view.githubrepositories.ui.repositories

import org.junit.Assert.*
import org.junit.Test


/**
 * Test for [TempGithubUserName.Test]
 */

class TempGithubUserNameTest {

    @Test
    fun test_success_add_name() {

        val testTempGithubUserName = TempGithubUserName.Test()

        var expected = "default name"
        var actual = testTempGithubUserName.currentName()

        assertEquals(expected, actual)

        val newCurrentName = "Bob"
        testTempGithubUserName.addName(newCurrentName)

        expected = "Bob"
        actual = testTempGithubUserName.currentName()

        assertEquals(expected, actual)
    }
}