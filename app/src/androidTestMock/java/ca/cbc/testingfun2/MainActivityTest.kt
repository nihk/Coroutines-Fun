package ca.cbc.testingfun2

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ca.cbc.testingfun2.data.TheBestJobEverProvider
import ca.cbc.testingfun2.ui.MainActivity
import ca.cbc.testingfun2.util.EspressoIdlingResource
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// fixme: How to avoid manually clearing the database each time in @After?
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var activityScenario = activityScenarioRule<MainActivity>()

    @Before
    fun setUp() {
        // Alternatively make this dagger dependency.
        IdlingRegistry.getInstance().register(EspressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource)
        activityScenario.scenario.onActivity {
            it.viewModel.clearGitHubJobs()
        }
    }

    @Test
    fun addARow() {
        onView(withId(R.id.button))
            .perform(click())

        onView(withText("A title"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun resultsWereShown() {
        onView(withId(R.id.recycler_view))
            .check(matches(atPosition(0, withChild(withText(TheBestJobEverProvider.TITLE)))))
    }

    private fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}